package algorithm.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-22 21:17
 * @Description:
 */
public class ImplementStackUsingQueues {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(null);
        stack.push(1);
        stack.push(2);
        stack.push(null);
        stack.push(3);
        stack.push(null);
//        int e = stack.pop();
//        System.out.println(e);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.top());
    }
}

class MyStack<E>{
    private Queue<E> queue;
    private boolean isZero = true;

    public MyStack(){
        this.queue = new LinkedList<>();
    }

    public void push(E x) {
        if (isZero){
            queue.offer(x);
        }else {
            E e = queue.poll();
            queue.offer(e);
            queue.offer(x);
            isZero = true;
        }
    }

    public E pop() {
        if (isZero){
            for (int i = 0; i < queue.size() - 1; i++) {
                E e = queue.poll();
                queue.offer(e);
            }
            return queue.poll();
        }
        isZero = true;
        return queue.poll();

    }

    public E top() {
        if (isZero){
            for (int i = 0; i < queue.size() - 1; i++) {
                E e = queue.poll();
                queue.offer(e);
            }
            isZero = false;
        }
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

class MyStack2 {
    private Queue main;
    private Queue temp;
    private int top;

    public MyStack2(){
        this.main = new LinkedList();
        this.temp = new LinkedList();
    }

    public void push(int x){
        this.main.offer(x);
        top = x;
    }


    public int pop(){
        while (main.size() > 1){
            top = (int) main.poll();
            temp.offer(top);
        }
        int e = (int) this.main.poll();
        Queue q = this.main;
        this.main = this.temp;
        this.temp = q;
        return e;
    }

    public int top(){
        return top;
    }

    public boolean empty(){
        return main.isEmpty() && temp.isEmpty();
    }

    public static void main(String[] args) {
        MyStack2 stack2 = new MyStack2();
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        stack2.push(4);
        System.out.println(stack2.top());
        stack2.push(5);
        System.out.println(stack2.top());
        System.out.println(stack2.pop());
        System.out.println(stack2.pop());
        System.out.println(stack2.pop());
        System.out.println(stack2.top());
        System.out.println(stack2.top());
    }

}


class MyStack3{
    private Queue queue;
    private int top;

    public MyStack3(){
        queue = new LinkedList();
    }

    public void push(int x){
        queue.offer(x);
        top = x;
    }

    public int pop(){
        for (int i = 0; i < queue.size() - 1; i++) {
            top = (int) queue.poll();
            queue.offer(top);
        }
        return (int) queue.poll();
    }

    public int top(){
        return top;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}


