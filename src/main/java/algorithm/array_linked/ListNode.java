package algorithm.array_linked;

/**
 * @Author: M˚Haonan
 * @Date: 2019-12-02 14:12
 * @Description: 链表节点
 */
public class ListNode {

    public int val;
    public ListNode next = null;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + "";
    }
}
