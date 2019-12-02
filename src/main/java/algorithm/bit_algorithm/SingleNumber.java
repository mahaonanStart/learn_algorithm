package main.java.algorithm.bit_algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-07 15:58
 * @Description:
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 *
 * Your main.java.algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * leetcode 136
 */
public class SingleNumber {

    /**
     * 利用位运算
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = nums[0];
        //遍历nums
        for (int i = 1; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }

    /**
     * 使用set
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)){
                set.remove(num);
            }
        }
        return set.iterator().next();
    }
}
