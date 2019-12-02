package main.java.algorithm.sort_algorithm;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 15:13
 * @Description: 归并排序算法
 */
public class MergeSort {

    public static void mergeSort(int[] nums, int low, int high, int []temp){
        if (low >= high){
            return;
        }
        int middle = (low + high) / 2;
        //使左边归并排序好
        mergeSort(nums, low, middle, temp);
        //使右边归并排序好
        mergeSort(nums, middle + 1, high, temp);
        //将左右两边子序列合并排序
        merge(nums, low, middle, high, temp);
    }

    public static void merge(int[] nums, int low, int mid, int high, int[] temp){
        //左序列指针
        int i = low;
        //右序列指针
        int j = mid + 1;
        //临时数组指针
        int t = 0;
        //左序列和右序列进行比较，并将每次的小的放入临时数组
        while (i <= mid && j<= high){
            if (nums[i] < nums[j]){
                temp[t ++] = nums[i ++];
            }else{
                temp[t ++] = nums[j ++];
            }
        }
        //左序列剩余元素放入临时数组中
        while (i <= mid){
            temp[t ++] = nums[i ++];
        }
        //右序列剩余元素放入临时数组中
        while (j <= high){
            temp[t ++] = nums[j ++];
        }
        //将临时数组指针归0
        t = 0;
        //将临时数组的元素放入原数组中
        while (low <= high){
            nums[low ++] = temp[t ++];
        }

    }

    public static void main(String[] args) {
        int [] nums = new int[]{2,1,3,5,8};
        int [] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
        System.out.println(Arrays.toString(nums));
    }

}
