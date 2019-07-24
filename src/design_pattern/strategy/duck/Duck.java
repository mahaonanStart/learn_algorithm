package design_pattern.strategy.duck;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-25 22:19
 * @Description: 鸭子的抽象类
 */
public abstract class Duck {

    public Duck() {
    }

    public void Quack(){
        System.out.println("~~ga~ga~~");
    }

    /**
     * 各种鸭子出现的行为
     */
    public abstract void display();

    public void swim(){
        System.out.println("~~I am swimming~~");
    }
}
