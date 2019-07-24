package algorithm.dynamic_program;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-18 22:57
 * @Description:
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 *
 * leetcode 10
 *
 */
public class RegularExpressionMatching {

    /**
     * 回溯递归算法
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        //如果p为空字符串的情形
        if (p.isEmpty()) return s.isEmpty();
        //第一个字符的匹配情况
        //s和p第一个字符相同，或者p的第一个字符为.
        boolean first_match = ((!s.isEmpty()) && ((s.charAt(0) == p.charAt(0)) || (p.charAt(0) == '.')));
        if (p.length() >= 2 && p.charAt(1) == '*'){
            //p的第二个字符为*的情况
            //此时，当下只存在两种情况，一种是p中x*没有匹配到s的第一个字符，这种情况就可以去掉p的前两个字符x*
            //第一种情况是，p中的x*匹配到了s的第一个字符，即s=abc p=a*bc，这种情况把s向后挪一位即可
            //通过保留 p 中的「*」，同时向后推移 s，来实现「*」将字符重复匹配多次的功能
            return (isMatch(s, p.substring(2))) || (first_match && isMatch(s.substring(1), p));
        }else{
            //没有*的情况
            //已经知道第一个字符的情况了，那么递归判断后续的即可
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }

    /**
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch2(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        //dp初始值
        dp[text.length()][pattern.length()] = true;
        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 动态规划
     * dp[i][k]表示text[i:]是否匹配pattern[k:],即text第i个字符开始是否匹配pattern的第k个字符开始
     * dp初始值dp[text.length][pattern.length] = true;
     * dp方程为:
     * 1. 假如k + 1为* ,即上述的存在*的情况
     * dp[i][k] = dp[i][k+2] || (first_match && dp[i + 1][k])
     * 2. 假如没有*
     * dp[i][k] = dp[i + 1][k + 1] && first_match
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch3(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        //dp初始值
        //pattern为空时，任何匹配都是false
        //这一步可以不写，因为默认new初始化时就都为false
        //写上思路更清晰
        for (int i = 0; i < dp.length; i++) {
            dp[i][pattern.length()] = false;
        }
        //text和pattern都为空时，匹配为false
        dp[text.length()][pattern.length()] = true;
        //因此，这里i从text.length开始，因为text为空时，仍有可能匹配到pattern
        //而k从pattern.length() - 1开始，因为上面初始化时值已经确定了，也因为pattern为空时，任何匹配都是失败的（除非text也为空）
        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                //判断第text的第i个字符和pattern的第k个字符是否匹配
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                //如果k+1为*，则dp[i][k] = dp[i][k+2] || (first_match && dp[i + 1][k])
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    //如果不为*
                    //dp[i][k] = dp[i + 1][k + 1] && first_match
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
