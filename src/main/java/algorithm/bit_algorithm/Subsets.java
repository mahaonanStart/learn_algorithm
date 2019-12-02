package main.java.algorithm.bit_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-07 11:34
 * @Description:
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * leetcode 78
 */
public class Subsets {


    /**
     * 使用位运算,真是神奇的思维
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        //获取可能的情况,每种元素有两种取法0，1，因此就是2的n次方
        int max = 1 << nums.length;
        //外层循环控制0到max，表示每种情况
        for(int i=0; i<max; i++) {
            List<Integer> subSets = new ArrayList<>();
            int k = i;
            int index = 0;
            //遍历当前情况的二进制，假设nums为{1,2,3}，i为4,即当前为第5种情况101，代表子集为[1,3]
            //这个循环的作用就是找出i的二进制中1的位置，1代表数组中该位置的元素被选中，加入子集中
            while(k > 0) {
                //如果k的最后一位是1,代表该位置的元素被选中
                if((k&1) == 1) {
                    subSets.add(nums[index]);
                }
                //k右移1位，直到k变为0
                k >>= 1;
                ++index;
            }
            allSubsets.add(subSets);
        }
        return allSubsets;
    }

    /**
     * 用迭代解决
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) return result;
        //添加空数组
        result.add(new ArrayList<>());
        Arrays.sort(nums);
        //遍历数组
        for (int num : nums) {
            //获取当前结果集合的长度
            int size = result.size();
            //遍历当前结果集合
            for (int i = 0; i < size; i++) {
                //将当前结果集合中的每个子集都复制到一个新的集合中
                List<Integer> list = new ArrayList<>(result.get(i));
                //将当前遍历到的num插入每个子集
                list.add(num);
                //添加到最后的结果集
                result.add(list);
            }
        }
        return result;
    }

    /**
     * 用数组递归解决
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        int[] lastArr = new int[nums.length - 1];
        System.arraycopy(nums, 0, lastArr, 0, nums.length - 1);
        List<List<Integer>> result = sub(lastArr, nums[nums.length - 1]);
        return result;
    }

    private List<List<Integer>> sub(int[] lastArr, int num) {
        //递归终止条件
        if (lastArr.length == 0){
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            result.add(Arrays.asList(num));
            return result;
        }
        //计算上一个的集合
        int [] arr = new int[lastArr.length - 1];
        System.arraycopy(lastArr, 0, arr, 0, lastArr.length - 1);
        List<List<Integer>> prev = sub(arr, lastArr[lastArr.length - 1]);
        List<List<Integer>> curr = new ArrayList<>(prev);
        //新的结果等于上一个的结果加上新加入的组合
        for (int i = 0; i < prev.size(); i++) {
            List<Integer> l = new ArrayList<>(prev.get(i));
            l.add(num);
            curr.add(l);
        }
        return curr;
    }

    /**
     * 用集合递归解决
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        //将数组转换为list，便于操作
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int num = list.remove(list.size() - 1);
        List<List<Integer>> result = subsets2(list, num);
        return result;
    }

    private List<List<Integer>> subsets2(List<Integer> list, int num) {
        //递归终止条件
        if (list.size() == 0){
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            result.add(Arrays.asList(num));
            return result;
        }
        int n = list.remove(list.size() - 1);
        List<List<Integer>> prev = subsets2(list, n);
        List<List<Integer>> curr = new ArrayList<>(prev);
        //新的结果等于上一个的结果加上新加入的组合
        for (int i = 0; i < prev.size(); i++) {
            List<Integer> l = new ArrayList<>(prev.get(i));
            l.add(num);
            curr.add(l);
        }
        return curr;
    }


    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int nums [] = new int[]{1,2,3};
        List<List<Integer>> subsets1 = subsets.subsets3(nums);
        System.out.println(subsets1);
    }
}
