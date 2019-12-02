package main.java.design_pattern.bridge.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 10:35
 * @Description:
 */
public class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("具体实现化角色A被访问");
    }
}
