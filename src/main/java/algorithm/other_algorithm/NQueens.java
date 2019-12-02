package main.java.algorithm.other_algorithm;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-05 17:39
 * @Description:
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * Example:
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 * 即N皇后问题
 * leetcode 51
 */
public class NQueens {

    /**
     * 使用位运算
     * @param n
     * @return
     */
    int count = 0;
    public int totalNQueues(int n) {
        //开始深度遍历
        dfs(0,0,0,0, n);
        return count;
    }

    private void dfs(int row, int col, int left, int right, int n) {
        //递归终止条件
        if (row >= n){
            count ++;
            return;
        }
        //获取全部空位(空位用1表示)
        int bits = (~(col | left | right)) & ((1<<n) - 1);
        while (bits > 0){
            //获取最后一个1
            //p中的1即为皇后所在的位置
            int p = bits & -bits;
            //遍历下一层
            dfs(row + 1, col | p, (left | p) << 1, (right | p) >> 1, n);
            //去掉最后一个1，继续下一次循环
            bits = bits & (bits - 1);
        }
    }

    public List<List<String>> solveNQueens2(int n) {
        //保存结果的集合
        List<List<String>> result = new ArrayList<>();
        String[] curr = new String[n];
        dfs2(result, curr, 0, 0, 0, 0, n);
        return result;
    }

    private void dfs2(List<List<String>> result, String[] curr, int row, int col, int left, int right, int n) {
        if (row >= n){
            result.add(Arrays.asList(curr.clone()));
            return;
        }
        int bits = (~(col | left | right)) & ((1<<n) - 1);
        while (bits > 0){
            int p = bits & -bits;
            //求出1所在的位数，即皇后的位置
            int bit = (int) (Math.log(p)/Math.log(2));
            //设置这一行的字符
            char[] r = new char[n];
            Arrays.fill(r, '.');
            r[bit] = 'Q';
            curr[row] = new String(r);
            dfs2(result, curr, row + 1, col | p, (left | p) << 1, (right | p) >> 1, n);
            //将1所在的位置消掉
            bits = bits & (bits - 1);
        }
    }


    /**
     * N皇后问题
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        //排除列的元素集合
        Set<Integer> col = new HashSet<>();
        //排除撇的元素集合
        Set<Integer> left = new HashSet<>();
        //排除捺的元素集合
        Set<Integer> right = new HashSet<>();
        //保存结果的集合
        List<List<String>> result = new ArrayList<>();
        //每种可能字符串的集合
        //这里用数组的好处，一是可以用clone方法，二是长度固定，这样在每列遍历时可以覆盖之前的结果，而用集合添加起来不方便
        //三是添加字符串方便，可以用Arrays的方法
        String[] curr = new String[n];
        dfs(result,0, curr, n, col, left, right);
        return result;
    }

    /**
     * 深度优先遍历，以行为深度，往深遍历
     * @param result    最终结果
     * @param row       深度的每一层
     * @param curr      当前列的字符串结果
     * @param n         传入的皇后阶数
     * @param col       排除列的元素集合
     * @param left      排除撇的元素集合
     * @param right     排除捺的元素集合
     */
    private void dfs(List<List<String>> result, int row, String[] curr, int n, Set<Integer> col, Set<Integer> left, Set<Integer> right) {
        //递归终止条件
        if (row == n) {
            //这里必须复制一份数组，不然curr后面被修改时，前面存入的curr也会被修改，最后就会出现一样的结果
            result.add(Arrays.asList(curr.clone()));
            return;
        }
        //对于每一行遍历每一列
        for (int j = 0; j < n; j++) {
            //去掉排除的位置
            if (col.contains(j) || left.contains(row + j) || right.contains(row - j)) continue;
            //如果该行该列添加了皇后q，设置排除的元素集合
            col.add(j);
            left.add(row + j);
            right.add(row - j);
            //设置这一行的字符串
            char[] r = new char[n];
            Arrays.fill(r, '.');
            r[j] = 'Q';
            curr[row] = new String(r);
            //进行下一行的深度遍历
            dfs(result, row + 1, curr, n, col, left, right);
            //每完成一列，将排除集合初始化
            col.remove(j);
            left.remove(row + j);
            right.remove(row - j);
        }
    }

    public static void main(String[] args) {
        NQueens queens = new NQueens();
        List<List<String>> lists = queens.solveNQueens(4);
        System.out.println(lists);
    }
}
