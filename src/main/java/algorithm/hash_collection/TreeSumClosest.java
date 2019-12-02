package main.java.algorithm.hash_collection;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-21 21:31
 * @Description:
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * leetcode 16
 */
public class TreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        //初始化三数之和与target的差值
        int minSub = nums[0] + nums[1] + nums[2] - target;
        for (int i = 0; i < nums.length - 2; i++) {
            if (minSub == 0) {
                break;
            }
            //左指针
            int j = i + 1;
            //右指针
            int k = nums.length - 1;
            while (j < k){
                //每次三数之和
                int sum = nums[i] + nums[j] + nums[k];
                int res = sum - target;
                if (res == 0){
                    minSub = 0;
                    break;
                }
                if (res < 0 && res > minSub){
                    j ++;
                    minSub = res;
                }else if (res < 0 && res <= minSub){
                    j ++;
                }else if (res > 0 && res < Math.abs(minSub)) {
                    k --;
                    minSub = res;
                }else if (res > 0 && res >= Math.abs(minSub)) {
                    k --;
                }
            }
        }
        return minSub + target;
    }

    /**
     * 优化一点写法，使代码更优雅，思路一样
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                //当前的距离小于之前的距离，则重新更新最接近的和
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }

}
