package main.java.algorithm.tree_graph;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-05 11:52
 * @Description: 求二叉树的最小深度(即最近的一个叶子节点所在的深度)
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its minimum depth = 2.
 *
 * leetcode 111
 */
public class MinimumDepthOfBinaryTree {
    /**
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return depth(root, 0);
    }

    /**
     *
     * @param node
     * @param depth
     * @return
     */
    private int depth(TreeNode node, int depth) {
        if (node == null) return depth;
        //保存当前深度到左子树
        int leDep = depth;
        //保存当前深度到右子树
        int riDep = depth;
        //求左子树的深度
        int leftDepth = depth(node.left, ++ leDep);
        //求右子树的深度
        int rightDepth = depth(node.right, ++ riDep);
        //返回二者最小的即为所求
        //这里注意必须是叶子结点，例如左节点为空，右节点有一个叶子结点，那么应该按右节点的深度算
        if (leftDepth == depth + 1){
            return rightDepth;
        }
        if (rightDepth == depth + 1){
            return leftDepth;
        }
        return Math.min(leftDepth, rightDepth);
    }

    /**
     * 更简洁的一种写法
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth2(root.left);
        int right = minDepth2(root.right);
        if (left == 0 || right == 0){
            return left + right + 1;
        }
        return Math.min(left, right);
    }

}
