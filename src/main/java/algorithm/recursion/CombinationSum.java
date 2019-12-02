package main.java.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-24 11:00
 * @Description:
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * leetcode 39
 */
public class CombinationSum {

    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates;
    private int len;

    /**
     * 回溯算法，递归的应用，有点类似于深度搜索
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum(target, 0, new Stack<>());
        return res;
    }

    /**
     *
     * @param remain 剩下的数
     * @param start 要加入的数的下标
     * @param stack 栈，用来存放已经加入的数
     */
    private void findCombinationSum(int remain, int start, Stack<Integer> stack) {
        if (remain == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }
        //remain - candidates[i] >= 0 意思是如果剩余的数小于0了，就没必要判断了
        for (int i = start; i < len && remain - candidates[i] >= 0; i++) {
            //将数加入
            stack.push(candidates[i]);
            //递归进行后续的判断
            findCombinationSum(remain - candidates[i], i, stack);
            //判断完成后，需要回退，即将前一次加入的数取出来
            stack.pop();
        }
    }

    /**
     * 从整体的角度去思考的话
     * 依次把每一个元素当做结果集合的第一个元素，即入第一个栈，然后递归判断之后的情形，判断完成后，将这个元素出栈
     * 循环判断后面的元素
     * @param remain
     * @param start
     * @param stack
     */
    private void findCombinationSum2(int remain, int start, Stack<Integer> stack) {
        if (remain == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }
        if (remain < 0) {
            return;
        }
        //remain - candidates[i] >= 0 意思是如果剩余的数小于0了，就没必要判断了
        for (int i = start; i < len; i++) {
            stack.push(candidates[i]);
            //递归进行后续的判断
            findCombinationSum(remain - candidates[i], i, stack);
            //判断完成后，需要回退，即将前一次加入的数取出来
            stack.pop();
        }
    }


    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int [] arr = new int[]{2, 3, 6, 7};
        System.out.println(combinationSum.combinationSum(arr, 7));
    }


}
