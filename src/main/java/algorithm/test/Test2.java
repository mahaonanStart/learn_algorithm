package main.java.algorithm.test;

public class Test2 {
    /**
     * 迭代思想
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode curr = dump;
        while (curr.next !=null && curr.next.next != null){
            ListNode first = curr.next;
            ListNode second = curr.next.next;
            curr.next = second;
            first.next = second.next;
            second.next = first;
            curr = first;
        }
        return dump.next;
    }

    /**
     * 递归算法
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        ListNode nextTwo = head.next.next;
        head.next.next = head;
        head.next = swapPairs2(nextTwo);
        return next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode reverse = swapPairs2(l1);
        System.out.println(reverse.next.next);      //4
    }
}
