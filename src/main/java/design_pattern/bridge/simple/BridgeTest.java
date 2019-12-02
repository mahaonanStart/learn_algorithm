package design_pattern.bridge.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 10:52
 * @Description:
 */
public class BridgeTest {

    public static void main(String[] args) {
        Implementor implementor = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(implementor);
        abs.operation();
    }
}
