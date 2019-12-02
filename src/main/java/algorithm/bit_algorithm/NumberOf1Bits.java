package algorithm.bit_algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-06 21:29
 * @Description:
 * Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).
 *
 * Input: 00000000000000000000000000001011
 * Output: 3
 * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 */
public class NumberOf1Bits {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0){
            if ((n & 1) == 1) count ++;
            n = n >>> 1;
        }
        return count;
    }

    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0){
            n = n & (n - 1);
            count ++;
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOf1Bits number = new NumberOf1Bits();
        int i = number.hammingWeight2(3);
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.forEach(System.out::println);
        System.out.println(i);
    }
}
