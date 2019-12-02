package main.java.algorithm.tree_graph;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-30 09:31
 * @Description: Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * leetcode 236
 * 寻找一个二叉树中两个节点的最近公共祖先
 *
 */
public class LowestCommonAncestorOfBinaryTree {

    /**
     * 利用递归实现
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果root为null，直接return null
        if (root == null) return null;
        //如果root为p或者root为q，那么root即为所求
        //这里也可以寻找到p，q元素
        if (root == p || root == q) return root;
        //分别递归求左子树节点和右子树节点的最近公共祖先
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //如果左为空,说明p，q都在右子树，那么right即为所求
        if (left == null) return right;
        //同理，右为空，说明p,q都在子树，那么left即为所求
        if (right == null) return left;
        //如果二者都不为空，说明，p，q分散在左右子树，root即为所求
        return root;
    }

    /**
     * 用迭代指针的方式,分别求出二者的父节点，然后判断最近父节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q){
        //定义一个栈，用来进行深度搜索寻找p和q
        Stack<TreeNode> stack = new Stack<>();
        //定义一个map，用来存放节点和其父节点的对应关系
        Map<TreeNode, TreeNode> map = new HashMap<>();
        //初始化stack和map
        stack.push(root);
        map.put(root, null);
        //遍历直到找到p和q
        while (!map.containsKey(p) || !map.containsKey(q)){
            //取出当前节点进行遍历
            TreeNode node = stack.pop();
            //如果左节点不为空
            if (node.left != null){
                //设置左节点及其父节点
                map.put(node.left, node);
                //将左节点入栈，方便往深遍历
                stack.push(node.left);
            }
            //右节点同理
            if (node.right != null){
                map.put(node.right, node);
                stack.push(node.right);
            }
        }
        //循环结束后，p，q和其经过的元素的所有父子关系都保存在map里面了
        //创建一个存放p祖先节点的集合
        Set<TreeNode> parents = new HashSet<>();
        //将p的所有祖先节点存入parents中
        while (p!=null){
            parents.add(p);
            //取得p的父亲节点，迭代放入,直到为null
            p = map.get(p);
        }
        //然后依次从q的最近祖先节点开始找，如果找到了就是最近公共祖先节点
        //q的第一个在parents中出现的祖先满足条件，跳出循环·
        while (!parents.contains(q)){
            q = map.get(q);
        }
        return q;
    }


}
