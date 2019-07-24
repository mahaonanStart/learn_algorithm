package algorithm.array_linked;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-11 22:25
 * @Description: 两数之和
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 例如：
 *
 *输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * leetcode 2
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode curr = result;
        while (true) {
            //l1和l2都遍历完后，退出循环
            if (l1 == null && l2 == null) break;
            //如果l1为空，说明l1遍历完了但是l2还没有，因此将l1节点设置为0
            if (l1 == null) {
                l1 = new ListNode(0);
            }
            //l2同理
            if (l2 == null) {
                l2 = new ListNode(0);
            }
            int val = l1.val + l2.val;
            l1 = l1.next;
            l2 = l2.next;
            //如果两个节点之和大于等于10
            if (val >= 10) {
                //当前位数的值为val取余10
                curr.next = new ListNode(val % 10);
                //如果l1为空，直接设为1
                if (l1 == null) {
                    l1 = new ListNode(1);
                //否则值+1
                }else {
                    l1.val += 1;
                }
            //小于10直接相加即可
            }else {
                curr.next = new ListNode(val);
            }
            curr = curr.next;
        }
        return result.next;
    }
}
