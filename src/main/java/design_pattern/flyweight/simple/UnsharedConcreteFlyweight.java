package main.java.design_pattern.flyweight.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 16:02
 * @Description: 非享元角色
 */
public class UnsharedConcreteFlyweight {

    private String info;

    public UnsharedConcreteFlyweight(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
