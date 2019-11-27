package design_pattern.bridge.practice2;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-07 21:49
 * @Description:
 */
public class Huawei implements Brand{
    @Override
    public void open() {
        System.out.println("华为手机开机了");
    }

    @Override
    public void close() {
        System.out.println("华为手机关闭了");
    }

    @Override
    public void call() {
        System.out.println("华为手机再打电话");
    }
}
