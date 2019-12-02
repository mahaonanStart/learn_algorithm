package main.java.algorithm.sort_algorithm;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 10:48
 * @Description: 插入排序算法实现
 */
public class InsertSort {

    public static void insertSort(int[] nums){
        //外层循环控制每次要插入的数，从下标1开始到length - 1
        for (int i = 1; i < nums.length; i++) {
            //内层循环比较要插入的数和之前序列，插入对应的位置
            int insert = nums[i];
            int j = i - 1;
            //循环执行条件为j没有遍历完且插入的元素小于比较的元素，
            while (j >= 0 && insert < nums[j]){
                // 如果插入的数小于比较的元素，将元素右移一位
                nums[j + 1] = nums[j];
                j --;
            }
            //j + 1即为应该插入的位置，插入数据
            nums[j + 1] = insert;
        }
    }

    public static void main(String[] args) {
        int [] nums = new int[]{4,3,1,6,7,2};
        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
