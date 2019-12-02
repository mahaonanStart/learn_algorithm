package algorithm.sort_algorithm;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 14:50
 * @Description: 简单选择排序算法
 */
public class SelectionSort {

    public static void selectionSort(int[] nums){
        //外层循环控制遍历的次数，n个元素需要遍历n - 1次
        for (int i = 0; i < nums.length - 1; i++) {
            //存放当前序列中的最小值的下标
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]){
                    min = j;
                }
            }
            //交换最小值与最前面未排序的数字
            if (i != min){
                int tem = nums[i];
                nums[i] = nums[min];
                nums[min] = tem;
            }
        }
    }

    public static void main(String[] args) {
        int [] nums = new int[]{2,1,3,5,8,4,-1,1,4,2,5,6,123,513,512};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
