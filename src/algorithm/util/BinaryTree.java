package algorithm.util;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 20:54
 * @Description: 链表结构的二叉树
 */
public class BinaryTree {

    //根节点
    private TreeNode root;

    //临时存储前驱节点
    private TreeNode pre;

    //设置根节点
    public void setRoot(TreeNode root){
        this.root = root;
    }
    //获取根节点
    public TreeNode getRoot() {
        return root;
    }

    /**
     * 遍历线索化二叉树
     */
    public void threadIterate(){

        //存储当前遍历的节点
        TreeNode curr = root;
        while (curr != null){
            //循环找到最开始的节点
            while (curr.getLeftType() == 0){
                curr = curr.getLeftNode();
            }
            //打印当前节点的值
            System.out.println(curr.getData());
            //当当前节点有后继节点时，说明是线索化时指定的下一个节点
            while (curr.getRightType() == 1){
                curr = curr.getRightNode();
                System.out.println(curr.getData());
            }
            curr = curr.getRightNode();
        }
    }

    /**
     * 中序线索化二叉树
     */
    public void threadNodes(){
        threadNodes(root);
    }


    public void threadNodes(TreeNode node){
        if (node == null){
            return;
        }
        //线索化左子节点
        threadNodes(node.getLeftNode());
        //线索化当前节点
        //如果当前节点的左子节点为空
        if (node.getLeftNode() == null){
            //将左子节点指向前驱节点
            node.setLeftNode(pre);
            //修改左节点类型
            node.setLeftType(1);
        }
        //处理当前节点的前驱节点
        if (pre != null && pre.getRightNode() == null){
            //如果为空，设置为当前节点
            pre.setRightNode(node);
            pre.setRightType(1);
        }
        //将前驱节点设置为当前节点，方便下次使用
        pre = node;
        //线索化右子节点
        threadNodes(node.getRightNode());
    }

    public void frontShow(){
        root.frontShow();
    }

    public void middleShow(){
        root.middleShow();
    }

    public void afterShow(){
        root.afterShow();
    }

    public TreeNode frontSearch(int val){
        return root.frontSearch(val);
    }

    public TreeNode middleSearch(int val){
        return root.middleSearch(val);
    }

}
