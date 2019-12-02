package main.java.algorithm.other_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-10 11:17
 * @Description:
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * 将word1转换为word2的最少操作步数
 * leetcode 72
 */
public class EditDistance {
    /**
     * dp动态规划
     * dp状态的定义至关重要，是难点
     * 这里面dp状态是一个二维数组，表示word1前i个字符转换为word2前j个字符的最小步数
     * 然后是初始化dp状态，由于假如word1为0个字符,则转换为word2，最少需要i步
     * 最后是循环递推
     * dp[i][j] = min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1
     * 牢记dp状态的定义，然后我怎么根据前一步来递推下一步
     * 总之这个系列问题还是很难的，多多体会，理解
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        //定义dp状态
        //word1前i个字符转换为word2前j个字符的最小步数
        int[][] dp = new int[m + 1][n + 1];
        //初始化dp状态
        //假如word1为0个字符,则转换为word2，最少需要i步
        for(int i = 1; i <= n; i++){
            dp[0][i] = i;
        }
        //同理，假设word2为0个字符
        for(int i = 0; i <= m; i++){
            dp[i][0] = i;
        }
        //嵌套循环，写dp方程
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //dp方程
                //如果word1的第i个字符(i-1)和word2的第j个字符(j-1)相同，则不需要操作
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                //如果不同，则需要考虑如下情况，插入dp[i][j - 1]，删除dp[i - 1][j]，替换[i - 1][j - 1]
                }else{
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];


    }
    public int min(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }
}
