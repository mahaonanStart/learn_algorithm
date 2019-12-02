package design_pattern.bridge.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 10:36
 * @Description: 抽象化角色
 */
public abstract class Abstraction {

    protected Implementor imple;

    protected Abstraction(Implementor imple) {
        this.imple = imple;
    }


    public abstract void operation();

}
