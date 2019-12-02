package algorithm.stack_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-02 21:42
 * @Description:
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * leetcode 32
 */
public class LongestValidParentheses {
    Map<Character, Character> map = new HashMap<>();

    public LongestValidParentheses(){
        map.put(')', '(');
    }

    /**
     * 暴力破解法，会超时
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int max = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                String str = s.substring(i, j + 1);
                if (str.length() % 2 == 0 && isValidParentheses(str)) {
                    max = Math.max(max, str.length());
                }
            }
        }
        return max;
    }

    private boolean isValidParentheses(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ele = str.charAt(i);
            if (map.containsKey(ele)) {
                char left = stack.isEmpty() ? '#' : stack.pop();
                if (map.get(ele) != left) {
                    return false;
                }
            }else {
                stack.push(ele);
            }
        }
        return stack.isEmpty();
    }


    /**
     * 直接利用栈,思路很巧妙，很难想
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /**
     * 双向扫描,很巧妙
     * @param s
     * @return
     */
    public int longestValidParentheses3(String s) {
        //left为左括号的数目。right为右括号的数目
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            //遇到'(', left++
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            //如果左括号的数量等于右括号的数量，说明遇到合法的了,记录当前的最大长度
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
                //如果右括号的数量大于左括号的数量，说明该串后面不可能找到连续的合法括号了，因此将left和right都置为0,重新开始计数
            } else if (right > left) {
                left = right = 0;
            }
            //如果left > right，就继续遍历
        }
        //由于可能会出现这种情况'((())'，如果从左往右遍历可能会漏掉这种情况，得不到left==right的情况，因此从右往左遍历一次，可以把情况补足
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }


    /**
     * 动态规划
     * @param s
     * @return
     */
    public int longestValidParentheses4(String s) {
        int max = 0;
        //dp[i]定义为以下标 i 结尾的合法序列的最长长度
        int [] dp = new int[s.length()];
        //第一个肯定是不合法为0，因此从第二个开始
        //以左括号结尾的肯定是不合法的，为0
        for (int i = 1; i < s.length(); i++) {
            //如果当前括号是右括号
            if (s.charAt(i) == ')') {
                //如果前一个括号是左括号， dp[i] = dp[i - 2] + 2
                if (s.charAt(i - 1) == '(') {
                    dp[i] = ((i >= 2) ? dp[i - 2] : 0) + 2;
                    //如果前一个括号是右括号, 并且dp[i - 1]的前一个括号为左括号
                    //dp[i] = dp[i - 1] + 匹配的左括号前边的合法序列的长度 + 2
                }else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
