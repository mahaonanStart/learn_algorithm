package algorithm.practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-24 08:56
 * @Description: 练习求固定窗口的最大值数组
 */
public class Practice2 {

    /**
     *  利用优先队列求解（max heep）
     * @param nums
     * @param k
     * @return
     */
    public int[] maxWindow1(int [] nums, int k){
        if (k == 0){
            return new int[0];
        }
        //创建一个max heep
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (i1, i2)-> i2 - i1);
        //初始化结果保存数组
        int [] results = new int[nums.length - k + 1];
        //初始化max heep
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        //设置results的第一个元素
        results[0] = queue.peek();
        //设置results的初始化下标为1
        int index = 1;
        //遍历k之后的nums，设置之后的results
        for (int i = k; i < nums.length; i++) {
            queue.remove(nums[i - k]);
            queue.offer(nums[i]);
            results[index ++] = queue.peek();
        }
        return results;
    }

    /**
     * 利用双端队列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxWindow2(int[] nums, int k){
        if (k == 0){
            return new int[0];
        }
        //创建一个双端队列
        Deque<Integer> deque = new ArrayDeque<>();
        //初始化结果数组
        int[] results = new int[nums.length - k + 1];
        //初始化结果数组下标
        int index = 0;
        //遍历nums
        for (int i = 0; i < nums.length; i++) {
            //剔除超过k范围的窗口内的元素的下标
            while (!deque.isEmpty() && deque.peek() <= i - k){
                deque.pollFirst();
            }
            //从尾部查找，剔除小于新入队的元素的下标
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            //从尾部添加元素下标入队
            deque.offer(i);
            //将每次最大值保存到results
            if (i >= k - 1){
                results[index ++] = nums[deque.peek()];
            }
        }
        return results;

    }

    public static void main(String[] args) {
        int [] arr = new int[]{3,2,7,6};
        int k = 3;
        Practice2 s = new Practice2();
        int [] result = s.maxWindow2(arr, k);
        System.out.println(Arrays.toString(result));
    }

}
