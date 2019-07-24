package algorithm.test;

import algorithm.util.Graph;
import algorithm.util.Vertex;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-29 11:57
 * @Description: 测试自定义的无向图结构
 */
public class TestGraph {

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");
        Vertex v5 = new Vertex("E");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addEdge(v1, v3);
        graph.addEdge(v1, v4);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v5);
        int[][] rel = graph.getRelation();
        for (int[] ints : rel) {
            System.out.println(Arrays.toString(ints));
        }
        graph.breadthShow();
        graph.depthShow();
    }

}
