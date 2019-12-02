package main.java.algorithm.tree_graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-05 11:32
 * @Description: 求二叉树的最大深度
 *
 *Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its depth = 3.
 *
 * leetcode 104
 */
public class MaximumDepthOfBinaryTree {
    /**
     * 递归求解深度，分别求左右子树的depth，然后取最大值
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return getDepth(root, 0);
    }

    /**
     * 分别求左右子树的depth
     * @param node
     * @param depth
     * @return
     */
    private int getDepth(TreeNode node, int depth) {
        if (node == null) return depth;
        //保存当前深度到左子树
        int leDep = depth;
        //保存当前深度到右子树
        int riDep = depth;
        //求左子树的深度
        int leftDepth = getDepth(node.left, ++ leDep);
        //求右子树的深度
        int rightDepth = getDepth(node.right, ++ riDep);
        //返回二者最大的即为所求
        return Math.max(leftDepth, rightDepth);
    }


    /**
     * 递归更简单的一种写法
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 广度优先搜索解决方案
     * @return
     */
    public int maxDepth3(TreeNode root){
        if (root == null) return 0;
        //定义一个队列，用于广度优先
        Queue<TreeNode> queue = new LinkedList<>();
        //初始化最大深度
        int maxDepth = 0;
        //初始化队列
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            //遍历该层的元素
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            maxDepth ++;
        }
        return maxDepth;
    }

    /**
     * 深度优先遍历解决方案
     * @param root
     * @return
     */
    public int maxDepth4(TreeNode root){
        if (root == null) return 0;
        //定义一个栈，用于深度遍历
        Stack<TreeNode> stack = new Stack<>();
        //定义一个保存层数的栈
        Stack<Integer> level = new Stack<>();
        //初始化最大深度
        int maxDepth = 0;
        //初始化栈
        level.push(1);
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            //获取当前的层数
            int temp = level.pop();
            //如果该节点为叶子结点,刷新最大值
            if (node.left == null && node.right == null){
                if (temp > maxDepth) maxDepth = temp;
            }
            if (node.left != null){
                stack.push(node.left);
                level.push(temp + 1);
            }
            if (node.right != null){
                stack.push(node.right);
                level.push(temp + 1);
            }
        }
        return maxDepth;
    }


}
