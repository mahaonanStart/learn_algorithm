package design_pattern.composite.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-15 18:32
 * @Description:
 */
public class ShopTest {

    public static void main(String[] args) {
        Bags small, medium, big;
        big = new Bags("大袋子");
        medium = new Bags("中袋子");
        small = new Bags("小袋子");
        small.add(new Goods("苹果", 3, 3.3f));
        medium.add(new Goods("方便面", 1, 5.5f));
        medium.add(small);
        big.add(medium);
        big.add(new Goods("篮球",1, 20.2f));
        big.show();
        float calculate = big.calculate();
        System.out.println(calculate);
    }
}
