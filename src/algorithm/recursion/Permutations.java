package algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @Author: M˚Haonan
 * @Date: 2019-08-14 10:49
 * @Description:
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * leetcode 46 全排列
 *
 */
public class Permutations {

    /**
     * 全排列递归求解
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(res, nums, 0, nums.length - 1);
        return res;
    }

    private void permute(List<List<Integer>> res, int[] nums, int start, int end) {
        if (start == end) {
            List<Integer> curr = Arrays.stream(nums).boxed().collect(Collectors.toList());
            res.add(curr);
            return;
        }
        //依次把每个元素和当前位置交换，每次递归当前位置start+1，直到最后到最后位置
        for (int i = start; i <= end; i++) {
            swap(i, start, nums);
            permute(res, nums, start + 1, end);
            //将状态回置，进行下次交换
            swap(i, start, nums);
        }
    }

    private void swap(int i, int start, int[] nums) {
        int tem = nums[i];
        nums[i] = nums[start];
        nums[start] = tem;
    }

    /**
     * 回溯算法
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        generatePermution(res, nums, used,0, new Stack<>());
        return res;
    }

    /**
     *
     * @param res 最终结果list
     * @param nums  数组
     * @param used  使用过的元素下标标记
     * @param currSize  当前路径中的元素个数，初始为0，每次递归+1，直到将数组中元素不重复全部放入
     * @param path  当前路径栈，方便回溯，故选用栈
     */
    private void generatePermution(List<List<Integer>> res, int[] nums, boolean[] used, int currSize, Stack<Integer> path) {
        if (currSize == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                path.push(nums[i]);
                used[i] = true;
                generatePermution(res, nums, used, currSize + 1, path);
                path.pop();
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> permute = permutations.permute2(nums);
        System.out.println(permute);
    }
}
