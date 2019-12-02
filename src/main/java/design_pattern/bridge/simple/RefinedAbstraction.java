package main.java.design_pattern.bridge.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 10:41
 * @Description:
 */
public class RefinedAbstraction extends Abstraction{

    protected RefinedAbstraction(Implementor imple) {
        super(imple);
    }

    @Override
    public void operation() {
        System.out.println("扩展抽象化角色被访问");
        imple.operationImpl();
    }
}
