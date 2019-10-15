package design_pattern.prototype;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-10 10:31
 * @Description: 原型模式简单测试
 */
class Realizetype implements Cloneable {

    Realizetype() {
        System.out.println("具体原型创建成功");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功");
        return (Realizetype) super.clone();
    }
}

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype obj1 = new Realizetype();
        Realizetype obj2 = (Realizetype) obj1.clone();
        System.out.println("obj1 == obj2?" + (obj1 == obj2));
    }

}
