package main.java.algorithm.priority_queue;

import java.util.PriorityQueue;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-23 15:38
 * @Description: 求一个流式数据中的第k个最大值（优先队列）
 * 利用优先队列求解。java中的优先队列默认为min heep，即堆顶是整个队列中最小的元素。
 * 那么我们可以维护一个长度为k的优先队列，堆顶的即为第k个最大值。
 * 每次来了新元素和其进行比较，如果小于堆顶元素，直接抛弃，如果大于堆顶元素，将堆顶元素出队，新元素入队
 * 该队列会自动维护内部关系，使堆顶保持为第k个最大值
 */
public class KthLargestElementInStream2 {
    private PriorityQueue<Integer> queue;
    private int k;

    public KthLargestElementInStream2(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int n) {
        if (queue.size() < k){
            queue.offer(n);
        }else if (queue.peek() < n){
            queue.poll();
            queue.offer(n);
        }
        return queue.peek();
    }


    public static void main(String[] args) {
        int [] nums = new int[]{4, 5, 8, 2};
        KthLargestElementInStream kle = new KthLargestElementInStream(3, nums);
        System.out.println(kle.add(3));
        System.out.println(kle.add(5));
//        System.out.println(kle.add(-4));
    }

}
