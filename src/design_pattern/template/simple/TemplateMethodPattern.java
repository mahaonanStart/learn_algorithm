package design_pattern.template.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-16 11:28
 * @Description:
 */
abstract class AbstractClass {
    //模板方法
    public void templateMethod() {
        specificMethod();
        abstractMethod1();
        abstractMethod2();
    }
    //具体方法
    public void specificMethod() {
        System.out.println("抽象类中的具体方法被调用...");
    }
    //抽象方法
    public abstract void abstractMethod1();
    public abstract void abstractMethod2();
}

class ConcreteClass extends AbstractClass {

    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }
}
public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractClass tm = new ConcreteClass();
        tm.templateMethod();
    }
}
