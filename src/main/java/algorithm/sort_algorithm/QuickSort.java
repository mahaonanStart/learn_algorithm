package main.java.algorithm.sort_algorithm;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 09:31
 * @Description: 快速排序算法
 */
public class QuickSort {

    /**
     * 将从low到high的片段做到升序
     * @param nums
     * @param low
     * @param high
     */
    public static void quickSort(int[] nums, int low, int high){
        if (low >= high){
            return;
        }
        //交换并返回分界点
        int bounder = getBounder(nums, low, high);
        //将low-分界点元素进行快排
        quickSort(nums, low, bounder - 1);
        //将分界点-high进行快排
        quickSort(nums, bounder + 1, high);

    }

    /**
     * 交换并返回分界点
     * @param nums
     * @param low
     * @param high
     */
    public static int getBounder(int[] nums, int low, int high){
        //选取参考值
        int look = nums[low];
        while (low < high){
            //由于是左边选的参考值，因此从右边开始判断，当队尾元素大于参考值时，直接high向前移动即可
            while (low < high && nums[high] >= look){
                high --;
            }
            //比参考值小的元素直接放到low
            nums[low] = nums[high];
            //然后从左边开始判断
            while (low < high && nums[low] <= look){
                low ++;
            }
            //比参考值大的放到high
            nums[high] = nums[low];
        }
        //循环结束的条件是low=high，这时候把参考值放进去，即为分界值
        nums[low] = look;
        return low;
    }


    public static void main(String[] args) {
        int [] nums = new int[]{2,1,3,5,8};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
