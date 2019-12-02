package main.java.design_pattern.flyweight.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 16:01
 * @Description: 抽象享元角色
 */
public interface Flyweight {
    void operation(UnsharedConcreteFlyweight state);
}
