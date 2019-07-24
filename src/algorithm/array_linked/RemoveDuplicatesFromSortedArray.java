package algorithm.array_linked;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-27 22:06
 * @Description:
 *
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * leetcode 26
 *
 *
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * 判重移动法
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //判重的临时变量
        int tem = nums[0];
        //移动的次数，即重复的次数
        int move = 0;
        for (int i = 1; i < nums.length - move; i++) {
            if (nums[i] == tem) {
                for (int j = i - 1; j < nums.length - 1 - move; j++) {
                    nums[j] = nums[j + 1];
                }
                //移动次数+1
                move ++;
                //倒退回去重新判断
                i --;
            }else {
                tem = nums[i];
            }
        }
        return nums.length - move;
    }

    /**
     * 双指针法
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        //定义一个慢指针，代表不重复的最后一个元素的下标
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            //如果nums[i] != nums[j], 说明遇到了不重复的数，因此最后一个不重复的数的下标应该+1，同时把找到的不重复的数赋值给该位置
            if (nums[i] != nums[j]) {
                i ++;
                nums[i] = nums[j];
            }
            //如果nums[i] == nums[j], 说明遇到重复的元素，跳过当前的j，即不重复的最后一个元素的下标依然是之前的数
        }
        return i + 1;
    }
}
