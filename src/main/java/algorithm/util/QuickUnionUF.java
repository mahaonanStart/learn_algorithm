package main.java.algorithm.util;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-10 14:53
 * @Description: 并查集实现代码
 */
public class QuickUnionUF {

    /**
     * 定义一个数组，root[m] = n,代表m的老大是n，即m在n的集合中
     */
    private int[] roots;

    /**
     * 初始化并查集，每个元素指向自己，即自己是自己的老大
     * @param n
     */
    public QuickUnionUF(int n){
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    /**
     * 找到i的最大老大，即i的根节点，即指向自己的元素
     * @param i
     * @return
     */
    private int findRoot(int i){
        int root = i;
        //当自己的老大等于自己时,即找到了i的老大
        while (root != roots[root]){
            //每次找到root的老大，依次往上迭代，直到自己的老大等于自己
            root = roots[root];
        }
        /**
         * 路径压缩，优化结构
         * 从当前节点开始，依次往上遍历，把当前节点的老大变为root节点，直到遍历到root
         */
        while (i != roots[i]){
            int tem = roots[i];
            roots[i] = root;
            i = tem;
        }
        return root;
    }

    /**
     * 将两个并查集合并
     * @param p
     * @param q
     */
    public void union(int p, int q){
        //找到q的老大
        int qroot = findRoot(q);
        //找到p的老大
        int proot = findRoot(p);
        //然后将p的老大的老大变为q
        roots[proot] = qroot;
    }
}
