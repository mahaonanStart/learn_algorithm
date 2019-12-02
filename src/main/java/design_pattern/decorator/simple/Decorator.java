package main.java.design_pattern.decorator.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 12:10
 * @Description: 抽象装饰角色
 */
public class Decorator implements Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
