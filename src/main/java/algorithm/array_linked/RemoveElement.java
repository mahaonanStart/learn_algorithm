package main.java.algorithm.array_linked;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-28 23:51
 * @Description:
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *
 * leetcode 27
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        //[0,0,1,2,2,2,3,4]
        int i = -1;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                i ++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
