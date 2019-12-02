package algorithm.bit_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-07 10:32
 * @Description: Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 *
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * leetcode 231
 */
public class PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false;
        return Integer.bitCount(n) == 1;

    }

    public static void main(String[] args) {
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        boolean ofTwo = powerOfTwo.isPowerOfTwo2(3);
        System.out.println(ofTwo);
    }
}
