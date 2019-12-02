package main.java.design_pattern.builder.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 10:54
 * @Description: 抽象建造者：包含创建各个子部件的抽象方法
 */
public abstract class Builder {

    //创建产品对象
    protected Product product = new Product();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();


    //获取产品对象
    public Product getResult() {
        return product;
    }
}
