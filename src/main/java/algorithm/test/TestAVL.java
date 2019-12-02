package algorithm.test;

import algorithm.util.BinarySearchTree;
import algorithm.util.TreeNode;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-28 20:51
 * @Description: 测试平衡二叉树
 */
public class TestAVL {
    public static void main(String[] args) {
        BinarySearchTree avl = new BinarySearchTree();
        int [] nums = {1};
        for (int num : nums) {
            avl.add(new TreeNode(num));
        }
        System.out.println(avl.getRoot().height());
        avl.middleShow();
    }
}
