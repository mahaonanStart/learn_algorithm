package main.java.algorithm.sort_algorithm;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 09:08
 * @Description: 冒泡排序算法
 */
public class BubbleSort {

    public static void bubbleSort(int [] nums){
        //外层循环控制总移动轮数，n个元素需要移动n - 1轮
        for (int i = 0; i < nums.length - 1; i++) {
            //内层循环控制每次移动的元素
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]){
                    int tem = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tem;
                }
            }
        }
    }

    public static void main(String[] args) {
        int [] nums = new int[]{2,1,3,5,8};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
