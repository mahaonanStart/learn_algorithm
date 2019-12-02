package main.java.design_pattern.decorator.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 14:23
 * @Description:
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.operation();
        System.out.println("---------------------");
        Component component1 = new ConcreteDecorator(component);
        component1.operation();
    }
}
