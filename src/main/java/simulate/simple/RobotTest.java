package simulate.simple;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

/**
 * @Author: M˚Haonan
 * @Date: 2020-01-07 09:46
 * @Description: java Robot测试
 *
 * void delay (int ms) 睡眠指定的时间（类似于线程中sleep）
 *
 * void keyPress（int keycode） 按下指定的键
 * void keyRelease（int keycode） 释放指定的键
 *
 * void mousePress（int buttons） 按下一个或多个鼠标按键
 * void mouseRelease（int buttons） 释放一个活多个鼠标按键
 *
 * void mouseMove（int x，int y） 将鼠标移动到给定的屏幕坐标上
 * void mouseWheel（int wheelAmt） 滚动鼠标滑轮
 *
 */
public class RobotTest {

    public static void main(String[] args) throws Exception{
        Robot robot = new Robot();
        robot.mouseMove(700, 700);
        robot.delay(1000);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(300);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }
}
