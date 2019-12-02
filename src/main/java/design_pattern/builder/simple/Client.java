package design_pattern.builder.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 11:23
 * @Description: 客户类
 */
public class Client {
    public static void main(String[] args) {
        //指定哪一个具体构造者
        Builder builder = new HouseBuilder();
        //创建指挥者来构造产品
        Director director = new Director(builder);
        //创建产品
        Product product = director.construct();
        product.show();
    }
}

