package main.java.algorithm.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-22 22:57
 * @Description:
 */
public class LeetCode10 {

    /**
     * 递归解法
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (s.length() > 0) && ((p.charAt(0) == s.charAt(0)) || p.charAt(0) == '.');
        if (p.length() > 1 && p.charAt(1) == '*'){
            return isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p));
        }else{
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }

    /**
     * dp
     * dp[i][j] s的第i个字符开始的字符串匹配 p的第j个开始的字符串
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //初始化dp值
        dp[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean first_match = (i < s.length()) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j + 1 < p.length() && p.charAt(j + 1) == '*'){
                    dp[i][j] = dp[i][j + 2] || (first_match && dp[i + 1][j]);
                }else{
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}
