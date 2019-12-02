package main.java.design_pattern.singleton.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-27 21:56
 * @Description: 线程不安全的懒汉式
 * 缺点：会有线程安全问题
 */
public class Singleton3 {

    private static Singleton3 singleton3;
    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (singleton3 == null) {
            singleton3 = new Singleton3();
        }
        return singleton3;
    }

}
