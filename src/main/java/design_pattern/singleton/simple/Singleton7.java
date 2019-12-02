package design_pattern.singleton.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-28 21:49
 * @Description: 枚举单例
 */
public class Singleton7 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        instance.sayOk();

    }
}
enum Singleton {
    INSTANCE;
    public void sayOk() {
        System.out.println("ok");
    }
}


