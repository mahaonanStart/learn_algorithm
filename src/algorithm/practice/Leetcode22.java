package algorithm.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-26 20:39
 * @Description:
 */
public class Leetcode22 {

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        generateParenthesis(res, n, n,"");
        return res;
    }

    private void generateParenthesis(List<String> res, int left, int right, String str) {
        if (left == 0 && right == 0) {
            res.add(str);
        }else {
            //生成左括号的情形
            if (left > 0) {
                generateParenthesis(res, left - 1, right, str + "(");
            }
            //生成右括号的情形
            if (right > 0 && right > left) {
                generateParenthesis(res, left, right - 1, str + ")");
            }
        }
    }
}
