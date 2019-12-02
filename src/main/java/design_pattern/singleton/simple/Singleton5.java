package main.java.design_pattern.singleton.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-27 22:00
 * @Description: 双重检查懒汉式
 */
public class Singleton5 {
    private static volatile Singleton5 singleton5;

    private Singleton5() {}

    public static Singleton5 getInstance() {
        if (singleton5 == null) {
            synchronized (Singleton5.class) {
                if (singleton5 == null) {
                    singleton5 = new Singleton5();
                }
            }
        }
        return singleton5;
    }
}
