package algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-25 10:13
 * @Description:
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * leetcode 40
 */
public class CombinationSumTwo {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        getCombination(target, 0, candidates, new Stack<>());
        return res;
    }

    /**
     * 递归考虑问题，要有那么一种感觉
     * 每次的情形都是一样的，只不过初始条件不一样
     * 对于第一次递归来说，前面是空白的，是最原始的状态，对于这道题来说，第一次递归栈底为空
     * 对于后面的递归来说，前面是有初始值的，对于这道题来说，栈底不为空，有了前面的条件
     * 但是归根到底，逻辑都是一样的，所有的判断都是公用的，都是生效的，不比深入的去思考递归的具体情形，就着手于一次，着手于整体去思考问题
     * 每次都当做最开始的递归去思考问题
     * @param remain
     * @param start
     * @param candidates
     * @param stack
     */
    private void getCombination(int remain, int start, int[] candidates, Stack<Integer> stack) {
        if (remain == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i < candidates.length && (remain - candidates[i]) >= 0; i++) {
            //去重，在有序的前提下，可以进行如下的判断
            //也就是说在相同的数字不能重复的作为栈底元素（指数组中不同位置的相同元素，同一个位置不算）
            //例如 1 1 2 3  target = 4 满足条件的为(nums[0] nums[1] nums[2]), (nums[0], nums[3])
            //nums[0]作为了栈底元素，那么nums[1]就不可能作为栈底元素，一旦它作为了栈底元素，那么后面一定会出现重复的 1, 3
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            stack.push(candidates[i]);
            getCombination(remain - candidates[i], i + 1, candidates, stack);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        CombinationSumTwo combinationSumTwo = new CombinationSumTwo();
        int [] candidates = new int[]{2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> list = combinationSumTwo.combinationSum2(candidates, target);
        System.out.println(list);
    }
}
