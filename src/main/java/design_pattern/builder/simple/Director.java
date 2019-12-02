package main.java.design_pattern.builder.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 11:17
 * @Description: 指挥者：调用建造者的方法完成复杂对象的创建
 */
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    //产品构建与组装方法
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
