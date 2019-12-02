package main.java.algorithm.practice;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-26 20:51
 * @Description: 冒泡排序练习
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr){
        //外层循环控制排序次数,n个数字需要排序n - 1次
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环控制每次比较的数字下标
            for (int j = 1; j < arr.length - i; j ++){
                if (arr[j - 1] > arr[j]){
                    int tem = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tem;
                }
            }
        }
    }

    public static void main(String[] args) {
        int [] nums = new int[]{2,1,3,5,8,4};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
