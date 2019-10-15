package design_pattern.bridge.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 11:35
 * @Description:
 */
public abstract class Bag {

    protected Color color;
    public void setColor(Color color) {
        this.color = color;
    }

    public abstract String getName();
}
