package main.java.design_pattern.bridge.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 11:37
 * @Description:
 */
public class BagManager {

    public static void main(String[] args) {
        Color color = new Yellow();
        Bag bag = new HandBag();
        bag.setColor(color);
        String name = bag.getName();
        System.out.println(name);
    }
}
