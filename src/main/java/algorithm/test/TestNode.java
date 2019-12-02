package main.java.algorithm.test;

import main.java.algorithm.util.Node;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-23 22:50
 * @Description:   测试单链表
 */
public class TestNode {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.append(n2).append(n3).append(n4).append(n5);
        n2.removeNext();
        n2.after(n3);
        System.out.println(n1.next().next().getData());
    }


}
