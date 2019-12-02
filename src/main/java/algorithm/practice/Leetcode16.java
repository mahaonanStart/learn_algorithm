package main.java.algorithm.practice;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-23 15:42
 * @Description:
 */
public class Leetcode16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sub = nums[0] + nums[1] + nums[2] - target;
        //第一个数
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            if (sub == 0) break;
            while (j < k){
                int sum = nums[i] + nums[j] + nums[k];
                int res = sum - target;
                //sub = -3 res = -2
                if (res == 0){
                    sub = 0;
                    break;
                }
                if (res < 0){
                    j ++;
                }
                if (res > 0){
                    k --;
                }
                if (Math.abs(res) < Math.abs(sub)) {
                    sub = res;
                }
            }
        }
        return sub + target;
    }
}
