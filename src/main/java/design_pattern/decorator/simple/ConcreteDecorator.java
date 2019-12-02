package design_pattern.decorator.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 12:12
 * @Description: 具体装饰角色
 */
public class ConcreteDecorator extends Decorator{

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addedFunction();
    }

    public void addedFunction() {
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }
}
