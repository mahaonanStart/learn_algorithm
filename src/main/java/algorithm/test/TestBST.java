package main.java.algorithm.test;

import main.java.algorithm.util.BinarySearchTree;
import main.java.algorithm.util.TreeNode;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-28 15:29
 * @Description: 测试二叉搜索树
 */
public class TestBST {
    public static void main(String[] args) {
        int[] nums = {7, 3, 10, 12, 5, 1, 9};
        BinarySearchTree bst = new BinarySearchTree();
        for (int num : nums) {
            bst.add(new TreeNode(num));
        }
//        bst.middleShow();
        System.out.println(bst.search(7).getData());
        System.out.println(bst.search(100));
    }
}
