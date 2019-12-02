package main.java.algorithm.tree_graph;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-26 20:20
 * @Description: 验证二叉搜索树
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
 */
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }


public class ValidateBinarySearchTree {

     private static TreeNode prev;

    /**
     * 利用中序遍历,二叉搜索树中序遍历的结果一定是升序的
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        return middleShow(root);
    }

    public static boolean middleShow(TreeNode node){
        if (node == null) return true;
        //遍历左子树
        boolean result = middleShow(node.left);
        if (!result) return false;
        //遍历自己
        if (prev != null && prev.val >= node.val) return false;
        //迭代prev
        prev = node;
        //遍历右子树
        return middleShow(node.right);
    }

    /**
     * 利用递归判断是否为二叉搜索树
     * 左树的最大值 < 根节点 < 右树的最小值
     * @param root
     * @return
     */
    public static boolean isValidBST2(TreeNode root) {
        return recusive(root, null, null);
    }

    /**
     *
     * @param node
     * @param min   该子树的最小值
     * @param max   该子树的最大值
     * @return
     */
    public static boolean recusive(TreeNode node, Integer min, Integer max){
        if (node == null) return true;
        if (min != null && min >= node.val) return false;
        if (max != null && max <= node.val) return false;
        return recusive(node.left, min, node.val) && recusive(node.right, node.val, max);

    }

}
