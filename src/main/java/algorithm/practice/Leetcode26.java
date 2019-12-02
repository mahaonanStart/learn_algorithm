package main.java.algorithm.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-29 17:28
 * @Description:
 */
public class Leetcode26 {

    public int removeDuplicates(int[] nums) {
        //判重的标准
        int tem = nums[0];
        //移动次数
        int move = 0;
        for (int i = 1; i < nums.length - move; i++) {
            if (nums[i] == tem) {
                for (int j = i; j < nums.length - move; j++) {
                    nums[j - 1] = nums[j];
                }
                i --;
            }else {
                tem = nums[i];
            }
        }
        return nums.length - move;
    }

    public int removeDuplicates2(int[] nums) {
        //j代表不重复的元素的下标
        //0011222334
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                j ++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }
}
