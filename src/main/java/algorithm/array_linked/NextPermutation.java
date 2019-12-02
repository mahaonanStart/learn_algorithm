package algorithm.array_linked;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-01 22:03
 * @Description:
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * leetcode 31
 *
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 从右往左找到第一个大于之前数的数
     * 例如14231, 找到的即为23, 交换23得到14321, 然后将21重新排序
     * 31这样的已经不可能有更大的序列了，因此继续往前找，231就有更大的序列(312)
     * @param nums
     */
    public void nextPermutation2(int[] nums) {
        int temp;
        //i始终在j的左边，并且是从后往前找的，因此就保证了找到的永远是第一个满足条件的
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = nums.length - 1; j > i; j--) {
                if(nums[i] < nums[j]) {
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    Arrays.sort(nums, i + 1, nums.length);
                    return;
                }
            }
        }
        Arrays.sort(nums);
    }

    public void nextPermutation3(int[] nums) {
        int start = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + 1 < nums.length && nums[i] < nums[i + 1]) {
                start = i;
                break;
            }
        }
        //key为start开始后面的元素和start的差值, value为下标
        Map<Integer, Integer> map = new HashMap<>();
        if (start == -1) {
            Arrays.sort(nums);
        }else {
            for (int i = start + 1; i < nums.length; i++) {
                if (nums[i] > nums[start]) {
                    map.put(nums[i] - nums[start], i);
                }
            }
            Set<Integer> set = map.keySet();
            int min = Integer.MAX_VALUE;
            for (Integer integer : set) {
                if (integer < min) {
                    min = integer;
                }
            }
            int tem = nums[start];
            nums[start] = nums[map.get(min)];
            nums[map.get(min)] = tem;
            Arrays.sort(nums, start + 1, nums.length);
        }
    }

    public static void main(String[] args) {
        System.out.println("我爱你");
        synchronized (NextPermutation.class) {
            System.out.println(123);
        }
    }
}
