package algorithm.util;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 21:01
 * @Description: 链式存储的二叉树的每个节点
 */
public class TreeNode {

    //树的权
    private int data;
    //树的左子节点
    private TreeNode leftNode;
    //树的右子节点
    private TreeNode rightNode;
    //树的节点类型(0代表左右子节点，1代表被线索化处理)
    private int leftType;
    private int rightType;



    public void setData(int data) {
        this.data = data;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public TreeNode(int data){
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(TreeNode rightNode) {

        this.rightNode = rightNode;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }


    /**
     * 求树的高度
     * @return
     */
    public int height(){
        return Math.max(leftNode == null?0:leftNode.height(),rightNode == null?0:rightNode.height()) + 1;
    }

    /**
     * 求左子树的高度
     * @return
     */
    public int leftHeight(){
        if (leftNode == null) return 0;
        return leftNode.height();
    }

    /**
     * 求右子树的高度
     * @return
     */
    public int rightHeight(){
        if (rightNode == null) return 0;
        return rightNode.height();
    }

    /**
     * 前序遍历
     */
    public void frontShow(){
        System.out.println(data);
        if (leftNode != null){
            leftNode.frontShow();
        }
        if (rightNode != null){
            rightNode.frontShow();
        }
    }

    /**
     * 中序遍历
     */
    public void middleShow(){
        if ( leftNode != null){
            leftNode.middleShow();
        }
        System.out.println(data);
        if (rightNode != null){
            rightNode.middleShow();
        }
    }

    /**
     * 后序遍历
     */
    public void afterShow(){
        if (leftNode != null){
            leftNode.afterShow();
        }
        if (rightNode != null){
            rightNode.afterShow();
        }
        System.out.println(data);
    }


    /**
     *  前序查找
     */
    public TreeNode frontSearch(int val) {
        TreeNode result = null;
        if (val == data){
            return this;
        }
        if (leftNode != null){
            result = leftNode.frontSearch(val);
            if (result != null){
                return result;
            }
        }
        if (rightNode != null){
            result = rightNode.frontSearch(val);
        }
        return result;
    }

    /**
     * 中序查找
     * @param val
     * @return
     */
    public TreeNode middleSearch(int val){
        TreeNode result = null;
        if (leftNode != null){
            result = leftNode.middleSearch(val);
            if (result != null){
                return result;
            }
        }
        if (val == data){
            return this;
        }
        if (rightNode != null){
            result = rightNode.middleSearch(val);
        }
        return result;
    }

    /**
     * 添加节点使其成为二叉搜索树
     * @param node
     */
    public void add(TreeNode node) {
        if (node == null) return;
        //如果要添加的节点值小于当前节点的值
        if (node.data < this.data){
            //如果当前节点的左节点为空
            if (this.leftNode == null){
                this.leftNode = node;
            }else{
                //如果不为空
                this.leftNode.add(node);
            }
        }else{
            if (this.rightNode == null){
                this.rightNode = node;
            }else{
                this.rightNode.add(node);
            }
        }
        //判断当前节点是否为平衡二叉树
        //右旋(左左类型)
        if ((leftHeight() - rightHeight()) >= 2 ){
            //左右类型
            if (leftNode != null && leftNode.leftHeight() < leftNode.rightHeight()){
                //左子树先左旋
                leftNode.leftRotate();
                rightRotate();
            }else{
                rightRotate();
            }
        }else if ((leftHeight() - rightHeight()) <= -2){
            //右左类型
            if (rightNode != null && rightNode.leftHeight() > rightNode.rightHeight()){
                //右子树先右旋
                rightNode.rightRotate();
                //整体左旋
                leftRotate();
            }
            //左旋(右右类型)
            leftRotate();
        }
    }

    /**
     * 左旋转,使二叉树变为平衡二叉树
     */
    private void leftRotate() {
        TreeNode node = new TreeNode(data);
        node.leftNode = leftNode;
        node.rightNode = rightNode.leftNode;
        data = rightNode.data;
        rightNode = rightNode.rightNode;
        leftNode = node;
    }

    /**
     * 右旋,使二叉树变为平衡二叉树
     */
    private void rightRotate() {
        //1. 创建和根节点相同值的节点
        TreeNode node = new TreeNode(data);
        //2. 将新节点右子树设置为根节点右子树
        node.rightNode = rightNode;
        //3. 将新节点的左子树设置为根节点左子树的右子树
        node.leftNode = leftNode.rightNode;
        //4. 将根节点的值设置为根节点的左子树的值
        data = leftNode.data;
        //5. 将根节点的左子树设置为根节点的左子树的左子树
        leftNode = leftNode.leftNode;
        //6. 根节点的右子树设置为新节点
        rightNode = node;
    }

    /**
     * 二叉搜索树的查找
     * @param data
     * @return
     */
    public TreeNode search(int data) {
        if (this.data == data) return this;
        if (this.data < data){
            if (this.rightNode == null){
                return null;
            }else {
                return this.rightNode.search(data);
            }
        }else{
            if (this.leftNode == null) return null;
        }
        return this.leftNode.search(data);
    }
}
