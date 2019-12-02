package main.java.design_pattern.builder.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 11:02
 * @Description: 具体建造者：这里可能有多个，只要产品的部件相同，那么就可以自定义不同的具体建造方式，
 * 方便拓展
 */
public class ConcreteBuilder extends Builder{

    @Override
    public void buildPartA() {
        product.setPartA("partA");
    }

    @Override
    public void buildPartB() {
        product.setPartB("partB");
    }

    @Override
    public void buildPartC() {
        product.setPartC("partC");
    }
}
