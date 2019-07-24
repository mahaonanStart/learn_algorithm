package design_pattern.strategy.duck;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-25 22:22
 * @Description: 绿头鸭实现类
 */
public class GreenHeadDuck extends Duck{

    @Override
    public void display() {
        System.out.println("**绿头鸭**");
    }
}
