package design_pattern.decorator.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 12:09
 * @Description: 具体构件角色
 */
public class ConcreteComponent implements Component{
    public ConcreteComponent() {
        System.out.println("创建具体构件角色");
    }

    @Override
    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }
}
