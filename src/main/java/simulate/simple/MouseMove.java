package simulate.simple;

import java.awt.*;

/**
 * @Author: M˚Haonan
 * @Date: 2020-02-03 14:30
 * @Description: 鼠标自动移动
 */
public class MouseMove {

    private Robot robot = new Robot();

    public MouseMove() throws AWTException {
    }

    public void run() throws InterruptedException {
        while (true) {
            robot.mouseMove(100,200);
            Thread.sleep(5000);
            robot.mouseMove(600, 200);
            Thread.sleep(5000);
        }
    }


    public static void main(String[] args) throws Exception{
        MouseMove mouseMove = new MouseMove();
        mouseMove.run();
    }
}
