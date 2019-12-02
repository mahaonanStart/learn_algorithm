package main.java.algorithm.other_algorithm;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-09 15:37
 * @Description:
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * leetcode 322
 */
public class CoinChange {
    /**
     * dp动态规划
     * 这个问题就好比爬楼梯，对于这个例子来说，一共有11阶楼梯，一次能上1步，2步，3步，求所需的最小步数
     * dp状态dp[i]为上到第i阶楼梯，所需步数的最小值
     * dp方程为:
     * dp[i] = min(dp[i], dp[i-coins[j]] + 1)
     * 即上到第i阶台阶的最小步数，等于前一次行走的最小步数+1
     * 而前一次行走的最小步数为(当前台阶 - 每一步能走的台阶数)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        //最大值不可能超过amount
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        //外层是每次的走的台阶数
        for (int i = 1; i <= amount; i++) {
            //每步能走的步数
            for (int j = 0; j < coins.length; j++) {
                //如果每步走的步数小于台阶数
                if (coins[j] <= i) {
                    //当前台阶的最小步数等于前一台阶的最小步数 + 1
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        //如果amount阶台阶的步数 > amount，说明没有这样的走法
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * 递归求解, 会超时
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) return 0;
        int min = amount + 1;
        for (int i = coins.length - 1; i >= 0; i--) {
            if (coins[i] <= amount){
                int last = coinChange2(coins, amount - coins[i]);
                if (last != -1){
                    min = Math.min(min, last + 1);
                }
            }
        }
        return min > amount ? -1 : min;
    }




    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int i = coinChange.coinChange(new int[]{1,2}, 6249);
        System.out.println(i);
    }
}
