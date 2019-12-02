package main.java.algorithm.priority_queue;


import java.util.*;


/**
 * @Author: M˚Haonan
 * @Date: 2019-04-23 16:42
 * @Description:   求一个滚动的固定窗口中的最大值
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class SlidingWindowMaximum{

    /**
     * 使用优先队列（max heep）实现
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0){
            return new int[0];
        }
        //创建一个max heep
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        int [] results = new int[nums.length - k + 1];
        //初始化队列中的元素
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        results[0] = queue.peek();
        for (int i = 1; i < nums.length - k + 1; i++) {
            queue.remove(nums[i - 1]);
            queue.offer(nums[i + k - 1]);
            results[i] = queue.peek();
        }
        return results;
    }

    /**
     * 使用双端队列（Deque）实现
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (k == 0){
            return new int[0];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int [] results = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i ++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - k){
                deque.pollFirst();
            }
            //当入队为last入队时
            //这里一定要注意要从last开始判断,这样才能把队列中小于新元素的所有都消掉,因为新元素一定入队
            //假如从first开始,左边第一个大于新元素,那么中间有小的就会保留
            //从last开始,就可以保证每次最大值在first
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1){
                results[index ++] = nums[deque.peekFirst()];
            }
        }
        return results;
    }


    public static void main(String[] args) {
        int [] arr = new int[]{3,2,7,6};
        int k = 3;
        SlidingWindowMaximum s = new SlidingWindowMaximum();
        int [] result = s.maxSlidingWindow2(arr, k);
        System.out.println(Arrays.toString(result));
    }

}
