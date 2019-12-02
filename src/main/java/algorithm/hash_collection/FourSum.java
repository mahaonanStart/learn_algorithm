package algorithm.hash_collection;
import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-23 16:20
 * @Description: 四数之和
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * leetcode 18
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        //去重
        Set<List<Integer>> res = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            //重复的数字就不要去判断了
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //因为数组是有序的，因此一旦连续的4个数比target都大了，就不必找了，后面肯定不会找到满足条件的四个数
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            //同样数组是有序的，一旦第一个数字加上最后三个数字都比target小了，说明第一个数字取小了，直接跳过本次判断即可
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                //这三个判断和上面的同样的道理
                if (j - i > 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) continue;
                int p = j + 1;
                int q = nums.length - 1;
                while (p < q) {
                    int sum = nums[i] + nums[j] + nums[p] + nums[q];
                    if (sum < target) {
                        p ++;
                    }
                    if (sum > target) {
                        q --;
                    }
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[p ++], nums[q --]));
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        System.out.println(fourSum.fourSum(new int[]{1,0,-1,0,-2,2}, 0));
    }
}
