package main.java.algorithm.other_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-30 17:51
 * @Description: 贪心算法演示
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an main.java.algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * leetcode 122
 *
 * 给定一个数组，数组中每个元素代表每天的股票，每天都能买卖无数次，且最多能持有一股，求能收获的最大利润
 */
public class GreedyAlgorithm {
    /**
     * 贪心算法，只考虑当前利益
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profile = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]){
                profile += (prices[i + 1] - prices[i]);
            }
        }
        return profile;
    }
}
