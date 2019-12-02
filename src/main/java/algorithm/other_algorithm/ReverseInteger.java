package main.java.algorithm.other_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-17 21:23
 * @Description:
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within
 * the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem,
 * assume that your function returns 0 when the reversed integer overflows.
 *
 * leetcode 7
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 *
 *
 */
public class ReverseInteger {

    /**
     * 数学技巧
     * 1. 取得一个数字个位数 x % 10
     * 2. 消灭一个数字个位数 x / 10
     * 3. 2^31 - 1的个位数为7
     * 4. 8是-2^31的个位数
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0){
            //取得该数的最后一位数字
            int pop = x % 10;
            //去除最后一位后，重新得到x
            x /= 10;
            //避免溢出
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            //倒序后数字依次加入
            rev = rev * 10 + pop;
        }
        return rev;
    }

}
