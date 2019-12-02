package main.java.design_pattern.adapter.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 15:34
 * @Description: 类适配器模式
 * 这个例子中，可以用如下的比喻:
 * 中国人与外国人交流需要一个翻译
 * Target即外国人，我们需要实现的规范
 * Adaptee即中国人，我们旧的业务实现
 * ClassAdapter即翻译，我们通过这个翻译实现了中外交流，翻译将我们的话调用，外国人也听得懂
 */
//目标接口（即新的系统规范中的接口）
interface Target {
    void request();
}

//适配者接口（即我们希望适配目标接口规范的业务类）
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用!");
    }
}

//类适配器类,用继承来实现
class ClassAdapter extends Adaptee implements Target {

    @Override
    public void request() {
        specificRequest();
    }
}

public class ClassAdapterTest {

    public static void main(String[] args) {
        System.out.println("类适配器模式测试...");
        Target target = new ClassAdapter();
        target.request();
    }
}
