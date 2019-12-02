package main.java.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @Author: M˚Haonan
 * @Date: 2019-08-15 10:10
 * @Description:
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * leetcode 47 全排列2
 *
 */
public class PermutationsTwo {

    /**
     * 普通的全排列方法
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        getPermute(nums, 0, nums.length - 1, res);
        return res;
    }

    private void getPermute(int[] nums, int start, int end, List<List<Integer>> res) {
        if (start == end) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = start; i <= end; i++) {
            if (isRepeated(i, start, nums)) {
                continue;
            }
            swap(i, start, nums);
            getPermute(nums, start + 1, end, res);
            swap(i, start, nums);
        }
    }

    private boolean isRepeated(int curr, int start, int[] nums) {
        for (int i = start; i < curr; i++) {
            if (nums[i] == nums[curr]) {
                return true;
            }
        }
        return false;
    }


    private void swap(int i, int start, int[] nums) {
        int tem = nums[i];
        nums[i] = nums[start];
        nums[start] = tem;
    }

    /**
     * 回溯法
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        //遇到去重先排序，这样判断重复的时候我们只需要选取重复元素的第一个元素，后面的依次和前面的判断去重即可
        Arrays.sort(nums);
        boolean [] used = new boolean[nums.length];
        permute(res, nums, 0, new Stack<Integer>(), used);
        return res;
    }

    private void permute(List<List<Integer>> res, int[] nums, int currSize, Stack<Integer> path, boolean[] used) {
        if (currSize == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                //在进入一个新的分支之前，看一看这个数是不是和之前的数一样，如果这个数和之前的数一样，
                // 并且之前的数还未使用过，那接下来如果走这个分支，就会使用到之前那个和当前一样的数，就会发生重复，
                // 此时分支和之前的分支一模一样
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                path.push(nums[i]);
                used[i] = true;
                permute(res, nums, currSize + 1, path, used);
                used[i] = false;
                path.pop();
            }
        }
    }



    public static void main(String[] args) {
        PermutationsTwo permutations = new PermutationsTwo();
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> permute = permutations.permuteUnique2(nums);
        System.out.println(permute);
    }
}
