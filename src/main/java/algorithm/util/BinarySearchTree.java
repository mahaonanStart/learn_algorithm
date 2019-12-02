package algorithm.util;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-28 15:22
 * @Description: 二叉搜索树
 */
public class BinarySearchTree {

    TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    /**
     * 向二叉搜索树中添加节点
     * @param node
     */
    public void add(TreeNode node){
        if (root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void middleShow(){
        if (root == null) return;
        root.middleShow();
    }

    /**
     * 二叉搜索树的查找
     *
     * @return
     */
    public TreeNode search(int data){
        if (root == null) return null;
        return root.search(data);
    }

    /**
     * 删除一个节点
     * @param val
     */
    public void delete(int val){
        
    }
}
