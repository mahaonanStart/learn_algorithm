package main.java.algorithm.other_algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: M˚Haonan
 * @Date: 2019-08-13 11:21
 * @Description:
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 * leetcode 45 跳跃游戏二
 *
 */
class TreeNode{
    int val;
    int index;
    List<TreeNode> children;

    public TreeNode(int val, int index, List<TreeNode> children) {
        this.val = val;
        this.index = index;
        this.children = children;
    }

    public TreeNode() {
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", index=" + index +
                ", children=" + children +
                '}';
    }
}


public class JumpGameTwo {

    /**
     * 贪心算法
     * 其实就是一个简化版的BFS
     * 最小步数即为在保证广度的前提下的最小层级
     * 可以把整个问题转换为一个树结构，求最近叶子节点的深度
     * 比如说2 3 1 1 4
     * 2 代表他有两个子节点[3,1]
     * 3 代表他有3个子节点 [1, 1, 4]
     * end边界就是每层的最后一个子节点的位置
     * step即树的深度
     * 每次遍历完了当前层的所有元素，把树的深度 + 1
     * 这里运用的贪心算法的思想，每次找到使下一步最大的节点，因此可以直接确定最小深度的节点
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        //这里要注意一个细节，就是 for 循环中，i < nums.length - 1，少了末尾。因为开始的时候边界是第 0 个位置，
        // steps 已经加 1 了。
        for(int i = 0; i < nums.length - 1; i++){
            //找能跳的最远的,即每层的最大遍历范围，即BFS的广度
            maxPosition = Math.max(maxPosition, nums[i] + i);
            //这里其实是这样的意思，有一个边界，意味着之前走了一步，不一定是跳到边界的位置，而是跳到当前能够最大距离的一个位置
            //例如 2 3 1 1 4
            //第一次找的边界是1, 但是最大距离是从3开始到4的
            //i == end 意味着遍历完了当前层级的所有元素
            if( i == end){ //遇到边界，就更新边界，并且步数加一
                //然后更新下一级的广度
                end = maxPosition;
                //层级+1，进入下一层
                steps++;
            }
        }
        //最后层级即为最小步数
        return steps;
    }

    /**
     * 先转化为树，然后求最小高度，会超时
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        if (nums.length == 1) return 0;
        //BFS队列
        Queue<TreeNode> queue = new LinkedList<>();
        //初始化根节点
        TreeNode root = new TreeNode(nums[0], 0, null);
        queue.offer(root);
        while (!queue.isEmpty()) {
            //当前节点
            TreeNode curr = queue.poll();
            //当前节点的子节点数
            int len = curr.getVal();
            //当前元素的下标
            int index = curr.getIndex();
            //当前结束时数组下标
            int end = (index + len) >= nums.length ? nums.length - 1 : (index + len);
            //定义上一层节点的子节点list
            List<TreeNode> children = new ArrayList<>();
            for (int i = index + 1; i <= end; i++) {
                TreeNode node = new TreeNode(nums[i], i ,null);
                children.add(node);
                queue.offer(node);
            }
            curr.setChildren(children);
        }
        search(root);
        //然后求这个数的最小高度, 从0开始
        return getHeight(root, nums.length - 1);
    }


    public int getHeight(TreeNode root, int length) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        while (!queue.isEmpty()) {
            //当前层的节点数
            int size = queue.size();
            //遍历当前层的所有节点
            for (int i = 0; i < size; i++) {
                //依次取出当前层的元素
                TreeNode node = queue.poll();
                if (node.getIndex() == length) {
                    return height;
                }
                if (node != null) {
                    for (TreeNode child : node.children) {
                        queue.offer(child);
                    }
                }
            }
            height ++;
        }
        return height;
    }

    public void search(TreeNode root) {
        if (root == null) return;
        System.out.println(root.getVal());
        for (TreeNode child : root.getChildren()) {
            search(child);
        }
    }


    public static void main(String[] args) {
        JumpGameTwo jumpGameTwo = new JumpGameTwo();
        int[] nums = new int[]{2,1,3,1,4};
        System.out.println(jumpGameTwo.jump2(nums));
    }
}
