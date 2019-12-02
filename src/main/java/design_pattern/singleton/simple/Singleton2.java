package design_pattern.singleton.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-27 21:54
 * @Description: 静态代码块
 */
public class Singleton2 {
    private static Singleton2 singleton2;

    static {
        singleton2 = new Singleton2();
    }

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        return singleton2;
    }
}
