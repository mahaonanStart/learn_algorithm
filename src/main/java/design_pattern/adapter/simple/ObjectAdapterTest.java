package main.java.design_pattern.adapter.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 16:15
 * @Description:
 */
class ObjectAdapter implements Target {

    //用组合的方式来实现,注入业务类对象
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

public class ObjectAdapterTest {
    public static void main(String[] args) {
        System.out.println("对象适配器");
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }

}
