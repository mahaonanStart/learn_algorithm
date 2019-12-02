package main.java.algorithm.array_linked;

import algorithm.array_linked.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-26 21:02
 * @Description:
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * leetcode 23
 */
public class MergeKSortedLists {

    /**
     * 分治的思想
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int low, int high) {
        if (low == high) {
            return lists[low];
        }
        int mid = (low + high) / 2;
        ListNode left = mergeKLists(lists, low, mid);
        ListNode right = mergeKLists(lists, mid + 1, high);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left.val < right.val) {
            left.next = merge(left.next, right);
            return left;
        }else {
            right.next = merge(left, right.next);
            return right;
        }
    }

    /**
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode head = lists[i];
            while (head != null) {
                queue.add(head.val);
                head = head.next;
            }
        }
        ListNode res = new ListNode(0);
        ListNode curr = res;
        while (!queue.isEmpty()) {
            curr.next = new ListNode(queue.poll());
            curr = curr.next;
        }
        return res.next;
    }

    public ListNode mergeKLists3(ListNode[] lists) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode head = lists[i];
            while (head != null) {
                queue.add(head.val);
                head = head.next;
            }
        }
        ListNode res = new ListNode(0);
        ListNode curr = res;
        while (!queue.isEmpty()) {
            curr.next = new ListNode(queue.poll());
            curr = curr.next;
        }
        return res.next;
    }
}
