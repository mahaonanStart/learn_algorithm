package main.java.algorithm.practice;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-29 16:47
 * @Description:
 */
public class Leetcode18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        int len = nums.length;
        for (int i = 0; i < nums.length - 3; i++) {
            //重复的元素去除
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            for (int j =  i + 1; j < nums.length - 2; j++) {
                if (nums[j] + nums[len - 1] + nums[len - 2] + nums[i] < target) continue;
                if (nums[i] + nums[j + 1] + nums[i + 2] + nums[j] > target) break;
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
                        set.add(Arrays.asList(nums[i], nums[j], nums[p ++], nums[q --]));
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}
