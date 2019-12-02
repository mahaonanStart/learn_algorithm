package main.java.algorithm.bit_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-07 10:47
 * @Description:
 *
 * Given a non negative integer number num.
 * For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation
 * and return them as an array.
 * Example 1:
 *
 * Input: 2
 * Output: [0,1,1]
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * leetcode 338
 */
public class CountingBits {

    /**
     * 挨个统计每个数字中包含1的个数
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        //遍历0->num
        int[] result = new int[num + 1];
        for (int i = 0; i <= num ; i++) {
            result[i] = countOne(i);
        }
        return result;
    }

    public int countOne(int num){
        int count = 0;
        while (num != 0){
            num = num & (num - 1);
            count ++;
        }
        return count;
    }

    /**
     *
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        //遍历0->num
        int[] result = new int[num + 1];
        for (int i = 0; i <= num ; i++) {
            //如果为偶数，当前元素1的个数为该元素除以2的数 的1的个数
            //如果为奇数，当前元素1的个数为该元素除以2的数 的1的个数 + 1
            //合二为一一行代码表示就是如此
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

}
