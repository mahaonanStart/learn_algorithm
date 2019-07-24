package algorithm.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-29 11:49
 * @Description: 无向图的代码实现
 */
public class Graph {
    //图的顶点
    private Vertex[] vertex;
    //二阶矩阵，存放顶点之间的关系
    private int[][] relation;
    //当前顶点的下标
    private int index;

    public Graph(int size){
        vertex = new Vertex[size];
        relation = new int[size][size];
        for (int i = 0; i < size; i++) {
            relation[i][i] = 1;
        }
    }

    public Vertex[] getVertex() {
        return vertex;
    }

    public int[][] getRelation() {
        return relation;
    }

    /**
     * 添加顶点
     */
    public void addVertex(Vertex vertex){
        this.vertex[index ++] = vertex;
    }

    /**
     * 添加边
     * @param v1
     * @param v2
     */
    public void addEdge(Vertex v1, Vertex v2){
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < vertex.length; i++) {
            if (v1.getValue().equals(vertex[i].getValue())){
                index1 = i;
            }
            if (v2.getValue().equals(vertex[i].getValue())){
                index2 = i;
            }
        }
        relation[index1][index2] = 1;
        relation[index2][index1] = 1;
    }

    /**
     * 深度优先遍历
     */
    public void depthShow(){
        //定义一个栈，用于深度优先遍历
        Stack<Vertex> stack = new Stack<>();
        //当前遍历的节点的下标
        int curr = 0;
        this.vertex[0].setVisited(true);
        System.out.println(this.vertex[0]);
        stack.push(this.vertex[0]);
        //当栈不为空时
        while ( !stack.isEmpty() ){
            //这个循环控制当前节点广度访问多少, 这里节点的顺序可能不是连续的，因此必须从0开始判断，不然会出现遍历不完全的情况
            for (int i = 0; i < vertex.length; i++) {
                //判断是否可以访问到
                //如果可以访问到
                if (relation[curr][i] == 1 && !vertex[i].isVisited()){
                    System.out.println(vertex[i]);
                    stack.push(vertex[i] );
                    vertex[i].setVisited(true);
                    //当前节点往深一层
                    curr = i;
                    //将广度归0，因为当前节点已经更换，需要重新进行比较
                    i = 0;
                }
            }
            //循环结束时，当前节点的深度广度已经遍历完，需要将栈顶元素出栈，往回退的看之前元素广度是否还有
            stack.pop();
            if (!stack.isEmpty()){
                //栈出站后，需要变更当前元素
                curr = getPosition(stack.peek());
            }
        }
        //遍历完后将可见性初始化
        for (Vertex vertex1 : vertex) {
            vertex1.setVisited(false);
        }
    }

    /**
     * 广度优先遍历
     */
    public void breadthShow(){
        //定义一个队列，用于广度优先遍历
        Queue<Vertex> queue = new LinkedList<>();
        //当前遍历的节点的下标
        int curr = 0;
        this.vertex[0].setVisited(true);
        System.out.println(this.vertex[0]);
        queue.offer(vertex[0]);
        //遍历
        while (!queue.isEmpty()){
            //外层循环控制广度,当前节点应该访问的广度
            for (int i = 0; i < vertex.length; i++) {
                if (relation[curr][i] == 1 && !vertex[i].isVisited()){
                    System.out.println(vertex[i]);
                    queue.offer(vertex[i]);
                    vertex[i].setVisited(true);
                }
            }
            //当前节点遍历完之后，出队，继续下个节点
            queue.poll();
            if (!queue.isEmpty()){
                curr = getPosition(queue.peek());
            }
        }
        for (Vertex vertex1 : vertex) {
            vertex1.setVisited(false);
        }
    }


    /**
     * 返回指定节点的下标
     * @param v
     * @return
     */
    public int getPosition(Vertex v){
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == v){
                return i;
            }
        }
        return 0;
    }
}
