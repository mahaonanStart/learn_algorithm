package main.java.algorithm.test;

import main.java.algorithm.util.BinaryTree;
import main.java.algorithm.util.TreeNode;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 21:06
 * @Description: 测试自定义二叉树
 */
public class TestBinaryTree {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = new TreeNode(1);
        binaryTree.setRoot(root);
        TreeNode rLeftNode = new TreeNode(2);
        TreeNode rRightNode = new TreeNode(3);
        root.setLeftNode(rLeftNode);
        root.setRightNode(rRightNode);
        rLeftNode.setLeftNode(new TreeNode(4));
        TreeNode five = new TreeNode(5);
        rLeftNode.setRightNode(five);
        rRightNode.setLeftNode(new TreeNode(6));
        rRightNode.setRightNode(new TreeNode(7));
//        binaryTree.frontShow();
        TreeNode result = binaryTree.middleSearch(6);
//        System.out.println(result);
//        int [] tree = new int[]{1, 2, 3, 4, 5, 6, 7};
//        ArrayBinaryTree arrayBinaryTree =  new ArrayBinaryTree(tree);
        binaryTree.middleShow();
        binaryTree.threadNodes();
//        System.out.println(five.getRightNode().getData());
        System.out.println("--------------");
        binaryTree.threadIterate();
    }

}
