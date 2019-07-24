package algorithm.practice;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-26 21:09
 * @Description: 快速排序练习
 * 思想如下:
 * 取一个序列中的数作为参考数(例如左边第一个数),然后遍历数组中的元素，使其左边的数都小于自己，右边的数都
 * 大于自己，然后分别将左右两边进行递归重复这样的运算，最后得到的就是一个有序的序列
 * 算法复杂度：
 * 时间复杂度： 平均为O(nlogn)
 */
public class QuickSort {

    public static void quickSort(int[] arr, int low, int high){
        if (low >= high){
            return;
        }
        int bound = getBound(arr, low, high);
        quickSort(arr, low, bound - 1);
        quickSort(arr, bound + 1, high);
    }

    private static int getBound(int[] arr, int low, int high) {
        int look = arr[low];
        while (low < high){
            while (low < high && arr[high] >= look){
                high --;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= look){
                low ++;
            }
            arr[high] = arr[low];
        }
        arr[low] = look;
        return low;
    }

    public static void main(String[] args) {
        int [] nums = new int[]{2,1,3,5,8,4,-1,1,4,2,5,6,123,513,512};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }


}
