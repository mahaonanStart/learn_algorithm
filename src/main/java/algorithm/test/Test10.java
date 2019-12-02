package main.java.algorithm.test;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-13 21:37
 * @Description:
 */
public class Test10 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int [] newNum = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            newNum[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            newNum[nums1.length + i] = nums2[i];
        }
        Arrays.sort(newNum);
        if ((newNum.length & 1) == 1){
            return newNum[newNum.length / 2];
        }
        return (newNum[newNum.length / 2 - 1] + newNum[newNum.length / 2]) / 2.0;

    }
}
