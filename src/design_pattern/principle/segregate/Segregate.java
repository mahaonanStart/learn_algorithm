package design_pattern.principle.segregate;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-23 23:37
 * @Description: 接口隔离原则
 */

//拆分接口，使依赖时满足最小接口原则
//不同的接口完成不同的最小单元任务，保证接口的隔离
interface Interface1 {
    void operation1();
    void operation2();
    void operation3();
}

interface Interface2 {
    void operation4();
    void operation5();
}

class C implements Interface1 {

    @Override
    public void operation1() {
        System.out.println("C实现了operation1()");
    }

    @Override
    public void operation2() {
        System.out.println("C实现了operation2()");
    }

    @Override
    public void operation3() {
        System.out.println("C实现了operation3()");
    }
}

class A {
    public void user1(Interface1 i) {
        i.operation1();
    }
    public void user2(Interface1 i) {
        i.operation2();
    }
    public void user3(Interface1 i) {
        i.operation3();
    }
}


public class Segregate {
    public static void main(String[] args) {
        A a = new A();
        a.user1(new C());
        a.user2(new C());
        a.user3(new C());
    }

}
