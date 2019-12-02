package main.java.design_pattern.strategy.duck;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-25 22:23
 * @Description: 红头鸭实现类
 */
public class ReadHeadDuck extends Duck{
    @Override
    public void display() {
        System.out.println("**红头鸭**");
    }
}
