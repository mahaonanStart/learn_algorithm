package main.java.algorithm.hash_collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-24 11:15
 * @Description:
 */
public class TwoSum {

    /**
     * 利用map求解
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int ele = target - nums[i];
            if (map.containsKey(ele)){
                return new int[]{i, map.get(ele)};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
