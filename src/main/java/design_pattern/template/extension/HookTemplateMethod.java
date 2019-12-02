package design_pattern.template.extension;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-16 11:42
 * @Description: 含钩子方法的模板方法
 */
abstract class HookAbstractClass {

    public void templateMethod() {
        abstractMethod1();
        hookMethod1();
        if (hookMethod2()) {
            specificMethod();
        }
        abstractMethod2();
    }
    public void specificMethod() {
        System.out.println("抽象类中的具体方法被调用...");
    }

    //钩子方法1
    public void hookMethod1(){}

    //钩子方法2
    public boolean hookMethod2() {
        return true;
    }

    public abstract void abstractMethod1();
    public abstract void abstractMethod2();
}

class HookConcreteClass extends HookAbstractClass {

    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用");
    }

    @Override
    public void hookMethod1() {
        System.out.println("钩子方法1被重写");
    }

    @Override
    public boolean hookMethod2() {
        return false;
    }
}
public class HookTemplateMethod {
    public static void main(String[] args) {
        HookAbstractClass tm = new HookConcreteClass();
        tm.templateMethod();
    }
}
