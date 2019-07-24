package algorithm.array_linked;

import java.util.Optional;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-03 21:51
 * @Description:
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * leetcode 33
 */
public class SearchInRotatedSortedArray {

    int [] nums;
    int target;

    public int find_rotate_index(int left, int right) {
        if (nums[left] < nums[right])
            return 0;

        while (left <= right) {
            int pivot = (left + right) / 2;
            if (nums[pivot] > nums[pivot + 1])
                return pivot + 1;
            else {
                if (nums[pivot] < nums[left])
                    right = pivot - 1;
                else
                    left = pivot + 1;
            }
        }
        return 0;
    }

    public int search(int left, int right) {
    /*
    Binary search
    */
        while (left <= right) {
            int pivot = (left + right) / 2;
            if (nums[pivot] == target)
                return pivot;
            else {
                if (target < nums[pivot])
                    right = pivot - 1;
                else
                    left = pivot + 1;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        this.nums = nums;
        this.target = target;

        int n = nums.length;

        if (n == 0)
            return -1;
        if (n == 1)
            return this.nums[0] == target ? 0 : -1;

        //寻找旋转的节点（分割的节点），由于数组是升序的，因此旋转节点是数组中最小的元素
        int rotate_index = find_rotate_index(0, n - 1);

        // 如果旋转的点就是target，直接返回
        if (nums[rotate_index] == target)
            return rotate_index;
        // 如果数组没有旋转，也就是旋转的下标为0，那么用二分法查询满足的target
        if (rotate_index == 0)
            return search(0, n - 1);
        //如果target比数组的第一个元素小，说明目标元素在旋转节点的右边
        if (target < nums[0])
            // search in the right side
            return search(rotate_index, n - 1);
        //如果target比数组第一个元素大，说明在旋转节点的左边
        return search(0, rotate_index);
    }

}
