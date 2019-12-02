package main.java.algorithm.sort_algorithm;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 11:25
 * @Description: 希尔排序算法
 */
public class ShellSort {

    public static void shellSort(int[] nums){
        int k = nums.length;
        //迭代k，每次为k/3 + 1，直到k = 1结束
        while (k > 1){
            //定义k的递推公式
            k = k / 3 + 1;
            for (int i = k; i < nums.length; i++) {
                int insert = nums[i];
                int j = i - k;
                while (j >= 0 && insert < nums[j]){
                    nums[j + k] = nums[j];
                    j -= k;
                }
                nums[j + k] = insert;
            }
        }
    }

    public static void main(String[] args) {
        int [] nums = new int[]{2,1,3,5,8};
        shellSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
