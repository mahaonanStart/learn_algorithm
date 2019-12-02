package main.java.algorithm.util;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-23 22:38
 * @Description: 循环链表结构
 */
public class LoopNode {
    int data;
    LoopNode next;


    public LoopNode(int data){
        this.data = data;
        this.next = this;
    }

    /**
     * 插入一个节点作为当前节点的下一个节点
     * @param node
     */
    public void after(LoopNode node){
        LoopNode nextTwo = this.next;
        this.next = node;
        node.next = nextTwo;
    }

    public void removeNext(){
        LoopNode nextTwo = next.next;
        this.next = nextTwo;
    }

}
