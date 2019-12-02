package algorithm.other_algorithm;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-13 23:10
 * @Description: There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 * leetcode 4
 */
public class MedianOfTwoSortedArrays {

    /**
     * 暴力解决，直接合并数组并排序，求中位数
     * @param nums1
     * @param nums2
     * @return
     */
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

    /**
     * 时间复杂度为O(log(min(m,n)))
     * 最优解
     * 见leetcode
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-shu-b/
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //保证m <= n
        if (m > n){
            int[] temp = nums1; nums1 = nums2; nums2 = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        //二分法遍历0~m寻找合适的i
        while (iMin <= iMax){
            //i代表在0-m中寻找一个合适的i进行切分，能保证满足上述两个条件
            int i = (iMin + iMax) / 2;
            int k = halfLen - i;
            //这种情况意味着i太小，必须增大i，因此将iMin设置为i + 1，即二分法的思想
            if (i < iMax && nums2[k - 1] > nums1[i]){
                iMin = i + 1;
            //i太大，必须减小i
            }else if (i > iMin && nums1[i - 1] > nums2[k]){
                iMax = i - 1;
            //找到了i
            }else{
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[k - 1];
                }else if (k == 0){
                    maxLeft = nums1[i - 1];
                }else{
                    maxLeft = Math.max(nums1[i - 1], nums2[k - 1]);
                }
                if ((m + n) % 2 == 1) return maxLeft;
                int minRight = 0;
                if (i == m){
                    minRight = nums2[k];
                }else if (k == n){
                    minRight = nums1[i];
                }else{
                    minRight = Math.min(nums1[i], nums2[k]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
