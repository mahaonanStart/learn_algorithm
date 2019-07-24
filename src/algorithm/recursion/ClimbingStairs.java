package algorithm.recursion;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-08 16:26
 * @Description: You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * 爬n层楼梯，一次走一步或者两步，求所有可能的情况数量
 * leetcode 70
 */
public class ClimbingStairs {

    /**
     * 递归解法, 时间消耗比较多
     * 总的数量 = 第一次走一步的数量 + 第一次走两步的数量
     * 也就是
     * 总的数量 = （台阶 - 1）的数量 + （台阶 - 2）的数量
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //递归终止条件
        if (n <= 2) return n;
        return climbStairs( n - 1) + climbStairs(n - 2);
    }

    /**
     * 动态规划解法
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n <= 2) return n;
        //递推的初始条件
        int a = 1;
        int b = 2;
        //从第三位开始，依次递推
        for (int i = 3; i <= n; i++) {
            int c = a;
            a = b;
            b = c + b;
        }
        return b;
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs2(5));
    }
}
