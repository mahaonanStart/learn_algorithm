package design_pattern.builder.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 10:50
 * @Description: 产品对象，拥有很多复杂的零部件
 */
public class Product {

    private String partA;
    private String partB;
    private String partC;

    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    /**
     * 显示产品特性
     */
    public void show() {
        System.out.printf("产品组装好了，由%s, %s, %s组成", partA, partB, partC);
    }
}
