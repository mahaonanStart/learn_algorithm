package main.java.algorithm.bit_algorithm;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-07 17:35
 * @Description:
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 *
 * Example:
 *
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * leetcode 260
 */
public class SingleNumberThree {

    /**
     * 利用set集合
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)){
                set.remove(num);
            }
        }
        int[] result = new int[2];
        int index = 0;
        for (Integer integer : set) {
            result[index ++] = integer;
        }
        return result;
    }

    /**
     * 位运算
     * 例如1,1,2,2,3,5
     * 第一步后diff = 3^5 = 6(110)
     * diff ^ -diff = 110 ^ 010 = 010(2),即3和5在第二位上有不同,3(011)的第二位为1,5(101)的第二位为0
     * 然后根据这个分开,
     * 例如5 ^ 101 == 0,而3 ^ 101 != 0,这样就把两个数分到了两组,就可以分别求解了
     * @param nums
     * @return
     */
    public int[] singleNumber2(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        //获取最低位的1
        diff &= -diff;
        // Pass 2 :
        int[] rets = {0, 0};
        for (int num : nums)
        {
            //两个不同的数异或后,结果中二进制为1的位必然是两个不同的位异或形成的(一个为0,一个为1),因此,根据最低位1将这两个数分到2组中
            //分别求值
            //等于0的情况是原数中对应的位为0
            if ((num & diff) == 0) //
            {
                rets[0] ^= num;
            }
            else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }

}
