package algorithm.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-27 21:18
 * @Description:
 */
public class Leetcode24 {

    /**
     * 交换两个节点，并且把交换后的第二个节点指向递归的返回值
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode next = head.next;
        //1->2->3->4, 这里的意思是1的下一个指向4
        head.next = swapPairs(next.next);
        //这的意思是2的下一个节点指向1
        next.next = head;
        //返回2即可
        return next;
    }

    public ListNode swapPairs2(ListNode head) {
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode curr = dump;
        while (curr.next != null && curr.next.next != null) {
            ListNode first = curr.next;
            ListNode second = curr.next.next;
            curr.next = second;
            first.next = second.next;
            second.next = first;
            curr = first;
        }
        return dump.next;
    }
}
