package design_pattern.bridge.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 11:36
 * @Description:
 */
public class HandBag extends Bag{

    @Override
    public String getName() {
        return color.getColor() + "挎包";
    }
}
