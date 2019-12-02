package design_pattern.singleton.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-27 21:52
 * @Description: 单例模式之饿汉模式
 *
 * 缺点：可能会造成不必要的内存浪费
 */
public class Singleton1 {

    private static Singleton1 singleton1 = new Singleton1();

    //构造方法私有化
    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return singleton1;
    }

}
