package algorithm.tree_graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-05 10:35
 * @Description: 二叉树广度优先遍历实现
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * leetcode 102
 */
public class BinaryTreeLevelOrderTraversal {

    /**
     * 利用广度优先遍历二叉树
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        //定义一个嵌套list，用于存储结果
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        //定义一个队列，用于广度优先遍历
        Queue<TreeNode> queue = new LinkedList<>();
        //初始化队列，放入根节点
        queue.offer(root);
        //遍历queue
        while (!queue.isEmpty()){
            //当前队列的长度，即该层的节点数
            int size = queue.size();
            //创建一个list，存放当前层元素
            List<Integer> curr = new ArrayList<>();
            //遍历当前层的所有节点
            for (int i = 0; i < size; i++) {
                //依次取出队首元素，即遍历当前二叉树层
                TreeNode node = queue.poll();
                //放入当前层list
                curr.add(node.val);
                //将当前节点的左右子树加入队列
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            //将当前层list加入最后result
            result.add(curr);
        }
        return result;
    }

    /**
     * 利用深度优先遍历，并且分层
     * @param root
     * @return
     */
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) return result;
        order(root, 0);
        return result;
    }

    /**
     * 深度递归遍历每一层的每一个节点
     * @param node
     * @param level
     * @return
     */

    private void order(TreeNode node, int level) {
        if (node == null) return;
        //如果该层还没有元素,添加一个空的集合便于存
        if (result.size() == level){
            result.add(level, new ArrayList<>());
        }
        //将该层元素添加到对应的层
        result.get(level).add(node.val);
        //递归深度遍历下一层
        order(node.left, level + 1);
        order(node.right, level + 1);
    }


}
