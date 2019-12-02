package main.java.algorithm.util;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-29 11:32
 * @Description: 图顶点类
 */
public class Vertex {
    private String value;
    //是否已经被遍历(默认为false)
    private boolean visited;


    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "value='" + value + '\'' +
                '}';
    }
}
