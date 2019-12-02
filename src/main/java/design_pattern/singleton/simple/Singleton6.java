package design_pattern.singleton.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-27 22:16
 * @Description: 静态内部类的单例模式
 * 1. 外部类加载的时候，内部类不会加载
 * 2. 调用内部类属性的时候，才会加载，并且是线程安全的
 */
public class Singleton6 {

    private static class SingletonInner {
        private static Singleton6 singleton6 = new Singleton6();
    }

    private Singleton6() {}

    public static Singleton6 getInstance() {
        return SingletonInner.singleton6;
    }

    public static void main(String[] args) {
        Singleton6 instance = Singleton6.getInstance();
        System.out.println(instance);
    }
}
