package algorithm.sort_algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 17:08
 * @Description: 基数排序算法
 */
public class RadixSort {

    public static void radixSort(int[] nums){
        //求最大数
        int max = nums[0];
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
        }
        //求出最大位数，即需要遍历的次数
        int count = (max + "").length();
        //一个队列的数组，用于保存比较位数时的元素，为了考虑负数，增加10个，0-9对应负数，10到19对应正数
        Queue[] queues = new Queue[20];
        //初始化队列数组
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new LinkedList<Integer>();
        }
        //i控制位数，从个位开始，依次为0，1，2...count - 1
        for (int i = 0; i < count; i++) {
            //j代表数组中每次遍历的元素
            for (int j = 0; j < nums.length; j++) {
                //取出每个元素对应位数的值，放入对应队列中
                int index = getFigure(nums[j], i);
                queues[index].offer(nums[j]);
            }
            //每一位遍历完，按顺序取出来放入数组中
            int k = 0;
            for (Queue queue : queues) {
                while (queue.size() > 0){
                    nums[k ++] = (int) queue.poll();
                }
            }
        }
    }

    /**
     * 求一个数对应位的数字
     * @param num
     * @param n     从个位开始，依次为0，1，2...
     * @return
     */
    public static int getFigure(int num, int n){
        return (int) (num / Math.pow(10, n) % 10) + 10;
    }

    public static void main(String[] args) {
        int [] nums = new int[]{0,-1,112,-3312,53,8,1239,412,-412,-2432,12425134,21312,4124};
        radixSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
