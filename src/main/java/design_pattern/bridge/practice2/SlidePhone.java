package design_pattern.bridge.practice2;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-07 21:53
 * @Description:
 */
public class SlidePhone extends Phone{

    public SlidePhone(Brand brand, String style) {
        super(brand, style);
    }

    @Override
    public void open() {
        System.out.println(style);
        brand.open();
    }

    @Override
    public void close() {
        System.out.println(style);
        brand.close();
    }

    @Override
    public void call() {
        System.out.println(style);
        brand.call();
    }
}
