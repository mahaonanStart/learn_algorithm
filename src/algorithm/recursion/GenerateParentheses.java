package algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-05 15:59
 * @Description:
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * 生成合法的括号
 * leetcode 22
 */
public class GenerateParentheses {
    /**
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate("", list, n, 0, 0);
        return list;
    }

    /**
     * 递归生成序列
     * @param str
     * @param list
     * @param n
     * @param left
     * @param right
     */
    private void generate(String str, List<String> list, int n, int left, int right) {
        //递归终止条件
        if (left == n && right == n){
            list.add(str);
            return;
        }
        if (left < n){
            generate(str + "(", list, n, left + 1, right);
        }
        if (right < n && right < left){
            generate(str + ")", list, n, left, right + 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses ge = new GenerateParentheses();
        List<String> list = ge.generateParenthesis(3);
        System.out.println(list);
    }
}
