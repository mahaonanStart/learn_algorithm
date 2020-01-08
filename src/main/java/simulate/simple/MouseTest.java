package simulate.simple;

import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: M˚Haonan
 * @Date: 2020-01-08 11:04
 * @Description:
 */
public class MouseTest {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public void getMousePosition() {
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        System.out.println(width);
        System.out.println(height);

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.execute(() -> {
            int x = 0;
            int y = 0;
            while (true) {
                PointerInfo pinfo = MouseInfo.getPointerInfo();
                Point p = pinfo.getLocation();
                int mx = (int) p.getX();
                int my = (int) p.getY();
                if (mx != x || my != y) {
                    System.out.println("现在鼠标的位置为 x = " + mx + ", y = " + my);
                    x = mx;
                    y = my;
                }
            }
        });
    }



    public static void main(String[] args) {




    }
}
