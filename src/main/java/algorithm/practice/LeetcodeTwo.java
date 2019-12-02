package main.java.algorithm.practice;



/**
 * @Author: M˚Haonan
 * @Date: 2019-06-22 18:37
 * @Description: leetcode 第二题练习
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */

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

public class LeetcodeTwo {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode curr = result;
        while (true){
            if (l1 == null && l2 == null) break;
            if (l1 == null){
                l1 = new ListNode(0);
            }
            if (l2 == null){
                l2 = new ListNode(0);
            }
            int val = l1.val + l2.val;
            l1 = l1.next;
            l2 = l2.next;
            if (val >= 10){
                if (l1 == null){
                    l1 = new ListNode(1);
                }else{
                    l1.val = l1.val + 1;
                }
                curr.next = new ListNode(val % 10);
            }else{
                curr.next = new ListNode(val);
            }
            curr = curr.next;
        }
        return result.next;
    }
}
