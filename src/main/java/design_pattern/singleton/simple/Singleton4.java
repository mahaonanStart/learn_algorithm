package design_pattern.singleton.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-27 21:58
 * @Description: 懒汉式线程同步方法
 * 效率低，同时只能一个线程使用
 */
public class Singleton4 {

    private static Singleton4 singleton4;

    private Singleton4() {

    }

    public static synchronized Singleton4 getInstance() {
        if (singleton4 == null) {
            singleton4 = new Singleton4();
        }
        return singleton4;
    }
}
