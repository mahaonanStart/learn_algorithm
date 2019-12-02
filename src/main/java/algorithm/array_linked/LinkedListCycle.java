package algorithm.array_linked;
import algorithm.array_linked.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-22 14:42
 * @Description: 判断一个链表是否有环
 */
public class LinkedListCycle {
    /**
     * 使用set数据结构来判重,时间复杂度为o(n*1)
     * 空间复杂度为o(n)
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        set.add(head);
        ListNode curr = head;
        while (curr != null){
            ListNode node = curr.next;
            boolean hasCycle = set.add(node);
            if (!hasCycle){
                return true;
            }
            curr = node;
        }
        return false;
    }

    /**
     * 龟兔赛跑式解决方案
     * Therefore, the worst case time complexity is O(N+K), which is O(n).
     * Space complexity : O(1).
     * We only use two nodes (slow and fast) so the space complexity is O(1).
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        ListNode first = head;
        ListNode second = head;
        ListNode curr = head;
        while (first != null && head != null && second != null && second.next != null){
            first = first.next;
            second = second.next.next;
            if (first == second){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l1;
//        l3.next = l4;
//        l4.next = l5;
//        l5.next = l2;
        boolean flag = hasCycle2(l1);
        System.out.println(flag);
    }
}
