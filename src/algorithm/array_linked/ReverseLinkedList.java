package algorithm.array_linked;


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + "";
    }
}

public class ReverseLinkedList {

    public static ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public static ListNode reverseList2(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode re = reverseList2(next);
        next.next = head;
        return re;
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
        ListNode reverse = reverseList(l1);
        System.out.println(reverse.next.next);      //3
    }
}
