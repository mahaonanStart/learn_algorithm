package main.java.algorithm.array_linked;

/**
 * 二分法查找算法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int a [] = new int[]{1, 2};
        int index = binarySearch2(a, 3);
        System.out.println(index);
    }

    /**
     * 循环查找的算法
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch1(int [] arr, int target){
        int begin = 0;
        int end = arr.length - 1;
        int mid = (begin + end) / 2;
        while (begin <= end){
            if (arr[mid] == target){
                return mid;
            }
            if (arr[mid] < target){
                begin = mid + 1;
            }else{
                end = mid - 1;
            }
            mid = (begin + end) / 2;
        }
        return -1;
    }

    /**
     * 递归算法
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch2(int[] arr, int target){
        return binary(arr, target, 0, arr.length - 1);
    }

    public static int binary(int [] arr,int target, int begin, int end){
        int mid = (begin + end) / 2;
        if (arr[mid] == target){
            return mid;
        }
        if (begin >= end) return -1;
        if (arr[mid] < target){
            return binary(arr, target, mid + 1, end);
        }else if(arr[mid] > target){
            return binary(arr, target, begin, mid - 1);
        }

        return -1;
    }
}
