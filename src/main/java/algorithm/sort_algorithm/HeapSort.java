package main.java.algorithm.sort_algorithm;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-26 11:30
 * @Description: 堆排序算法
 */
public class HeapSort {

    public static void main(String[] args) {
        int [] nums = new int[]{4,3,1,6,7,2};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public static void heapSort(int[] nums){
        //需要排序的次数
        for (int size = nums.length; size > 1  ; size --) {
            //最后一个有子节点的节点
            int start = (size - 1) / 2;
            //将数组变为大顶堆
            for (int i = start; i >= 0 ; i --) {
                maxHeap(nums, size, i);
            }
            //将第一个位置的元素放入未排序的最后位置
            int tem = nums[0];
            nums[0] = nums[size - 1];
            nums[size - 1] = tem;
        }
    }


    /**
     * 将一个数组变为大顶堆
     * @param nums  数组
     * @param size  数组的长度
     * @param index 节点的位置
     */
    public static void maxHeap(int[] nums, int size, int index){
        //左子节点
        int leftNode = 2 * index + 1;
        //右子节点
        int rightNode = 2 * index + 2;
        //找到自己和左右中的最大值
        int max = index;
        if (leftNode < size && nums[leftNode] > nums[max]){
            max = leftNode;
        }
        if (rightNode < size && nums[rightNode] > nums[max]){
            max = rightNode;
        }
        if (max != index){
            //交换位置
            int tem =  nums[max];
            nums[max] = nums[index];
            nums[index] = tem;
            //子节点的顺序可能已经错乱，递归进行调整
            maxHeap(nums, size, max);
        }
    }


}
