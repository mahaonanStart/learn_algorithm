package algorithm.other_algorithm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-26 10:38
 * @Description:
 *
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 *
 * leetcode 41
 *
 */
public class FirstMissingPositive {

    /**
     * 笨办法，分情况讨论, 时间复杂度不满足，因为排序了
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;
        //先排序，便于判断
        Arrays.sort(nums);
        //对首尾进行判断，返回1的情形
        if ((nums[0] > 0 && nums[0] != 1) || nums[nums.length - 1] < 1) return 1;
        for (int i = 1; i < nums.length; i++) {
            //跳过非正整数和相同元素的情况
            if ((nums[i] <= 1) || (nums[i] == nums[i - 1])) continue;
            //判断-2 2 3这种序列，直接返回1
            if (nums[i] >= 2 && nums[i - 1] == -1) return 1;
            //如果不是差距为1的元素，说明缺失的最小正整数为上一个数+1
            if (nums[i] - nums[i - 1] != 1) return nums[i] + 1;
        }
        //循环结束还有没有返回值，说明最小正整数为最后一个数+1，即该序列是连续序列，且序列中包含1
        return nums[nums.length - 1] + 1;
    }

    /**
     * 用map求解
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        if (nums.length == 0) return 1;
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //跳过0和负数
            if (nums[i] <= 0) continue;
            //将数字放在map中
            map.put(nums[i], true);
        }
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            //从1开始遍历，如果map中不存在该值，说明就是所求的缺失最小正整数
            if (!map.containsKey(i) ) return i;
        }
        return 1;
    }

    public int firstMissingPositive3(int[] nums) {
        if (nums.length == 0) return 1;
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int i = 1;
        while (list.contains(i)) {
            i += 1;
        }
        return i;
    }

    /**
     * 桶排序思想
     * 每个元素放在自己应该在的位置上，由于是求最小的正整数，故最小为1，序列应该从1开始
     *
     * 因此最小的结果是1(给定数组中所有的数都比1小，或者所有的数都比1大),最大的结果是nums.length + 1(数组为从1开始的连续序列)
     * 因此数组中小于1的数和大于数组长度的数都是干扰项
     *
     * 因此假如我们把满足条件的数放在数组下标+1的位置上，那么最后排好序后，第一个出现 (数组下标 + 1) != 元素本身的位置，就是我们
     * 要寻找的位置。缺失的第一个正整数也就是该数组下标 + 1
     *
     * 假如有如下序列3 1 4 5
     * 可以画个图来理解，从概念上来讲，因为数组下标是从小到大的，从0开始一直到3，而我们的数至少从1开始
     * 假如我把1放在0的位置，3放在2的位置，4放在3的位置，5没位置放了，最后肯定是在1的位置，排好序如下
     * 1 5 3 4, 那么我们再次遍历这个数组，就会发现，我们在数组下标为1的位置不是2，意味着我们缺少了2这个正整数，因为数组下标是从0开始的，
     * 因此我们可以断定缺失的最小正整数就是2，即index + 1
     * 假如我们遍历完都没有找到满足条件的下标，那么就意味着我们把所有的数都放在了正确的位置，因此这是一个从1开始的连续序列，那么缺失的
     * 最小正整数就是length + 1
     * @param nums
     * @return
     */
    public int firstMissingPositive4(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //这个再写个循环，因为交换到这个位置的元素不一定满足i + 1，需要再次交换，直到最后满足i+1,或者交换来一个在判断条件之外的元素
            //即（小于1的和大于数组长度的元素）
            while (nums[i] > 0 && nums[i] <= nums.length && nums[(nums[i] - 1)] != nums[i]) {
                swap(i, nums[i] - 1, nums);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }

    private void swap(int i, int j, int[] arr) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    public static void main(String[] args) {
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        int[] arr = new int[]{1,1};
        System.out.println(firstMissingPositive.firstMissingPositive4(arr));
    }
}
