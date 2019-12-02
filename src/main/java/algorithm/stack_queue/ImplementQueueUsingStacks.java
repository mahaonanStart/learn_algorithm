package main.java.algorithm.stack_queue;
import java.util.Stack;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-22 20:44
 * @Description: 用队列实现栈的数据结构
 */
public class ImplementQueueUsingStacks {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        System.out.println(queue.pop());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.size());
    }

}

class MyQueue<E>{

    private Stack<E> input;
    private Stack<E> output;

    public MyQueue(){
        this.input = new Stack<>();
        this.output = new Stack<>();

    }

    public void push(E x) {
        input.push(x);
    }

    public E pop() {
        peek();
        return output.pop();
    }

    public int size(){
        return input.size() + output.size();
    }

    public E peek() {
        if (output.isEmpty()){
            while (!input.isEmpty()){
                E e = input.pop();
                output.push(e);
            }
        }
        return output.peek();
    }

    public boolean empty() {
        if (input.isEmpty() && output.isEmpty()){
            return true;
        }
        return false;
    }
}
