package design_pattern.bridge.practice2;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-07 21:52
 * @Description: 抽象类，用来聚合实现接口
 */
public abstract class Phone {

    protected Brand brand;
    protected String style;

    public Phone(Brand brand, String style) {
        this.brand = brand;
        this.style = style;
    }

    public abstract void open();

    public abstract void close();

    public abstract void call();
}
