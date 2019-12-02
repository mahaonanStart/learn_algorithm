package main.java.algorithm.priority_queue;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-23 11:54
 * @Description:    求一个流式数据中的第k个最大值（自己的笨办法）
    主要思路是：
    1. 先将之前的数组进行排序，然后取前k个从大到小放入数组，那么初始条件满足，第k个最大的元素即为数组的最后一个元素
    2. 每次新来一个数字，将其和最后一个元素进行比较，如果小于等于最后一个元素，直接抛弃，第k个最大的元素即为数组的最后一个元素
    3. 如果大于最后一个元素，替换最后一个元素，然后依次和之前的元素进行比较，将小于其的元素往后挪一位，直到遇到比其大的元素或比较完毕停止
    。返回现在数组的最后一个元素即为所求
    思路不难，只是在处理k和刚开始长度的时候问题颇多，浪费很多时间，最后算马马虎虎处理完成
 */
public class KthLargestElementInStream {
    private int k;
    private int [] nums;
    private int real;

    public KthLargestElementInStream(int k, int[] nums) {
        this.k = k;
        Arrays.sort(nums);
        this.nums = new int[k];
        real = k > nums.length ? nums.length: k;
        for (int i = 0; i < real; i++) {
            this.nums[i] = nums[nums.length - 1 - i];
        }
    }

    public int add(int val) {
        if (real < k){
            this.nums[k - 1] = val;
            real = k;
        }else{
            if (val <= this.nums[k - 1]){
                return this.nums[k - 1];
            }
            this.nums[k - 1] = val;
        }
        for (int i = k - 2; i >= 0; i--) {
            if (this.nums[i] < val){
                this.nums[i + 1] = this.nums[i];
                this.nums[i] = val;
            }
        }
        return this.nums[k - 1];
    }

    public static void main(String[] args) {
        int [] nums = new int[]{0, 6};
        KthLargestElementInStream kle = new KthLargestElementInStream(1, nums);
        System.out.println(kle.add(10));
        System.out.println(kle.add(1));
//        System.out.println(kle.add(-4));
    }
}
