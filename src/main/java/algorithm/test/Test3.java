package algorithm.test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-23 21:27
 * @Description: 测试双端队列
 */
public class Test3 {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        //等同于offerLast
        deque.offer(1);
        deque.offer(2);
        deque.offer(3);
        deque.offer(4);
        deque.offer(5);
        //peekFirst()
        System.out.println(deque.peek());
        //pollFirst()
        System.out.println(deque.poll());
    }
}
