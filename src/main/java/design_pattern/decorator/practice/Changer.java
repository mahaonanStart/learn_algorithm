package main.java.design_pattern.decorator.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 14:49
 * @Description: 抽象装饰角色
 */
public class Changer implements Morrigan{

    Morrigan m;

    public Changer(Morrigan m) {
        this.m = m;
    }
    @Override
    public void display() {
        m.display();
    }

}