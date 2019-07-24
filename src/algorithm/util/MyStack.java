package algorithm.util;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-23 11:19
 * @Description:  使用数组创建栈的数据结构
 */
public class MyStack {

    private int[] elements;

    public MyStack(){
        elements = new int[0];
    }

    /**
     * 添加一个元素
     * @param x
     */
    public void push(int x){
        int [] newArr = new int[elements.length + 1];
        System.arraycopy(elements, 0, newArr, 0, elements.length);
        newArr[elements.length] = x;
        elements = newArr;
    }

    public int pop(){
        int ele = elements[elements.length - 1];
        int [] newArr = new int[elements.length - 1];
        System.arraycopy(elements, 0, newArr, 0, elements.length - 1);
        elements = newArr;
        return ele;
    }

    public int peek(){
        int ele = elements[elements.length - 1];
        return ele;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(5);
        stack.push(6);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.peek());
    }

}
