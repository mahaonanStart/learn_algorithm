package main.java.algorithm.practice;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-26 21:01
 * @Description: 选择排序练习
 * 思想如下：
 * 找到一个序列中最小的值，将其与序列的开始未排序位置交换
 */
public class SelectionSort {

    public static void selectionSort(int[] arr){
        //n个元素需要交换n - 1次
        for (int i = 0; i < arr.length - 1; i++) {
            //保存最小元素的下标
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]){
                    min = j;
                }
            }
            if (min != i){
                int tem = arr[i];
                arr[i] = arr[min];
                arr[min] = tem;
            }
        }
    }

    public static void main(String[] args) {
        int [] nums = new int[]{2,1,3,5,8,4,-1,1,4,2,5,6,123,513,512};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
