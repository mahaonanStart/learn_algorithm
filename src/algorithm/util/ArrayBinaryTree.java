package algorithm.util;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-26 10:28
 * @Description: 顺序存储的二叉树
 */
public class ArrayBinaryTree {
    private int[] data;

    public ArrayBinaryTree(int[] data){
        this.data = data;
    }

    /**
     * 前序遍历
     */
    public void frontShow(){
        this.frontShow(0);
    }

    public void frontShow(int index){
        if (data == null || data.length == 0) return;
        if (index < data.length){
            System.out.println(data[index]);
        }
        if ((2 * index + 1) < data.length){
            frontShow(2 * index + 1);
        }
        if ((2 * index + 2) < data.length){
            frontShow(2 * index + 2);
        }
    }
}
