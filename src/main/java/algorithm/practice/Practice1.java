package main.java.algorithm.practice;

import java.util.PriorityQueue;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-24 08:51
 * @Description: 练习求第k个最大值
 */
public class Practice1 {
    private PriorityQueue<Integer> queue;
    private int k;

    public Practice1(int [] nums, int k){
        this.k = k;
        queue = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val){
        if (queue.size() < k){
            queue.offer(val);
        }else if(queue.peek() < val){
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int [] nums = new int[]{4, 5, 8, 2};
        Practice1 kle = new Practice1(nums, 3);
        System.out.println(kle.add(3));
        System.out.println(kle.add(5));
    }

}
