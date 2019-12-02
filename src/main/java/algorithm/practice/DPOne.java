package main.java.algorithm.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-19 20:54
 * @Description: 动态规划练习1
 */
public class DPOne {


    public boolean isMatch(String s, String p) {
        //定义dp状态，即s从第i个字符开始匹配p从第j个字符开始
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //初始化dp值, 即当s，p都为空时true
        dp[s.length()][p.length()] = true;
        //i为字符，倒着开始比较，先从空开始
        for (int i = s.length(); i >= 0; i--) {
            //j为匹配式，由于匹配式为空时都为false（除了s，p都为空）
            for (int j = p.length() - 1; j >= 0; j--) {
                //s的第i个字符和p的第j个字符相等的条件
                boolean first_match = (i < s.length()) && ((s.charAt(i) == p.charAt(j)) || (p.charAt(j) == '.'));
                //假如j + 1为*, 有两种情况
                if ((j + 1) < p.length() && p.charAt(j + 1) == '*'){
                    dp[i][j] = dp[i][j + 2] || (first_match && dp[i + 1][j]);
                }else{
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}
