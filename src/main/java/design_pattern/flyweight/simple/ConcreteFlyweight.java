package main.java.design_pattern.flyweight.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 16:03
 * @Description: 具体享元角色
 */
public class ConcreteFlyweight implements Flyweight{

    private String key;

    public ConcreteFlyweight(String key) {
        this.key = key;
        System.out.println("具体享元" + key + "被创建");
    }

    @Override
    public void operation(UnsharedConcreteFlyweight outState) {
        System.out.println("具体享元" + key + "被调用");
        System.out.println("非享元信息是：" + outState.getInfo());
    }
}
