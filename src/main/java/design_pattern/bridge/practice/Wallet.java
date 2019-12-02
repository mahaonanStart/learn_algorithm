package main.java.design_pattern.bridge.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 11:37
 * @Description:
 */
public class Wallet extends Bag{
    @Override
    public String getName() {
        return color.getColor() + "钱包";
    }
}
