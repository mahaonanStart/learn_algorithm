package main.java.algorithm.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-30 16:07
 * @Description: Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * leetcode 169
 * 求一个数组中出现次数大于n/2的元素
 */
public class MajorityElement {

    /**
     * 利用map统计数量
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        //创建一个map，数字和数量的对应关系
        Map<Integer, Integer> map = new HashMap<>();
        //遍历数组
        for (int num : nums) {
            //如果get(num)为空，就把value放进入，如果不为空，就执行lambda函数，即get(num) + value
            map.merge(num, 1, (a, b)-> a+b);
        }
        //返回value大于n/2的key
        int [] ele = new int[1];
        map.forEach((k, v) -> {
            if (v > nums.length/2) ele[0] = k;
        });
        return ele[0];
     }

    /**
     * 利用分治递归策略
     * @param nums
     * @return
     */
     public int majorityElement2(int[] nums){
         return  majority(nums, 0, nums.length - 1);
     }

    /**
     * 求从low-high数量最多的元素
     * @param nums
     * @param low
     * @param high
     * @return 返回元素
     */
    private int majority(int[] nums, int low, int high) {
         //如果数组只有一个元素，返回该元素即可
         if (low == high){
             return nums[low];
         }
         int mid = (low + high) / 2;
         //求左边序列的majority
         int left = majority(nums, low, mid);
         //求右边序列的majority
         int right = majority(nums, mid + 1, high);
         //如果左右都是相同的元素，直接返回
         if (left == right) return left;
         //计算左边最多的元素在整个序列中的个数（因为右边可能也有这个元素）
         int leftCount = count(nums, left, low, high);
         //同理计算右边
         int rightCount = count(nums, right, low ,high);
         return leftCount > rightCount ? left : right;
    }

    /**
     * 求low到high指定元素的个数
     * @param nums
     * @param num 指定元素
     * @param low
     * @param high
     * @return 返回元素的个数
     */
    private int count(int[] nums, int num, int low, int high) {
        int count = 0;
        for (int i : nums) {
            if (i == num) count ++;
        }
        return count;
    }

    /**
     * 利用排序做
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums){
        //对数组进行排序
        Arrays.sort(nums);
        //因为必存在一个元素的数量大于n/2，因此数组中间的元素即为所求
        return nums[nums.length / 2];
    }

}
