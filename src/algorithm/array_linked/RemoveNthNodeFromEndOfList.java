package algorithm.array_linked;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-24 21:43
 * @Description: Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * leetcode 19
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) return null;
        ListNode curr = head;
        int len = 0;
        while (curr != null){
            len ++;
            curr = curr.next;
        }
        head = remove(head, len - n);
        return head;
    }

    /**
     * 删除链表指定位置的节点
     * @param head
     * @param index
     */
    private ListNode remove(ListNode head, int index) {
        if (index == 0) {
            return head.next;
        }
        ListNode curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return head;
    }


    /**
     * 双指针法
     * 定义两个指针，前n - 1次，只移动第二个指针，然后开始同时移动
     * 最后前面的指针移动到末尾时，要删除的元素即为第一个指针所指向节点的下一个节点
     * 注意考虑一种特殊情况，即要删除的元素是链表的第一个节点时，需要另外判断
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        //第一个指针
        ListNode first = head;
        //第二个指针
        ListNode second = head;
        int count = 0;
        while (second.next != null) {
            count ++;
            second = second.next;
            if (count > n) {
                first = first.next;
            }
        }
        //特殊情况，删掉的元素为第一个元素
        if (count == (n - 1)) return head.next;
        first.next = first.next.next;
        return head;
    }

}
