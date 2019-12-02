package main.java.algorithm.tree_graph;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-30 14:08
 * @Description: 寻找一个二叉搜索树中两个节点的最近公共祖先
 */
public class LowestCommonAncestorOfBinarySearchTree {

    /**
     * 利用二叉搜索树的特性实现
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        //如果p，q都分布在根节点的左边，则递归往下调用
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        //右边同理
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        //其他情况都返回root（）
        return root;
    }

}
