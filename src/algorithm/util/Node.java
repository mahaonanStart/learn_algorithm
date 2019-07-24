package algorithm.util;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-23 22:45
 * @Description: 单链表结构
 */
public class Node {

    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }

    public Node next(){
        return next;
    }

    public int getData(){
        return data;
    }

    public Node append(Node node){
        Node curr = this;
        while (curr.next != null){
            curr = curr.next;
        }
        curr.next = node;
        return this;
    }

    /**
     * 往当前节点追加一个节点
     */
    public void after(Node node){
        node.next = this.next;
        this.next = node;
    }

    /**
     * 删除当前节点之后的一个节点
     */
    public void removeNext(){
        this.next = this.next.next;
    }
}
