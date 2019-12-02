package main.java.algorithm.tree_graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-27 14:03
 * @Description: 赫夫曼树的代码实现
 */

class Tree implements Comparable<Tree>{

    int data;
    Tree leftNode;
    Tree rightNode;
    Tree(int data){
        this.data = data;
    }

    @Override
    public int compareTo(Tree o) {
        return this.data - o.data;
    }
}


public class HuffmanTree {

    /**
     * 将一个数组转换成一颗赫夫曼树
     * @param nums
     * @return
     */
    public static Tree huffmanTree(int[] nums){
        //创建一个list，保存每次的节点
        List<Tree> list = new ArrayList<>();
        //添加初始元素到list中
        for (int num : nums) {
            list.add(new Tree(num));
        }
        while (list.size() > 1){
            //对list中的元素进行升序排序
            Collections.sort(list);
            //取最小的两个节点，设置父节点
            Tree left = list.get(0);
            Tree right = list.get(1);
            Tree parent = new Tree(left.data + right.data);
            parent.leftNode = left;
            parent.rightNode = right;
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        int [] nums = {2, 5, 26, 7, 4, 100, 107};
        Tree huffman = huffmanTree(nums);
        System.out.println(huffman.data);
    }
}
