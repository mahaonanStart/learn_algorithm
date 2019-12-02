package design_pattern.builder.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 11:25
 * @Description: 具体房子建造者
 */
public class HouseBuilder extends Builder{

    @Override
    public void buildPartA() {
        product.setPartA("灯");
    }

    @Override
    public void buildPartB() {
        product.setPartB("床");
    }

    @Override
    public void buildPartC() {
        product.setPartC("地板");
    }
}
