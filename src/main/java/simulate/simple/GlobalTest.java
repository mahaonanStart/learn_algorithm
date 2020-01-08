package simulate.simple;

import lombok.Data;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeMonitorInfo;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @Author: M˚Haonan
 * @Date: 2020-01-08 14:41
 * @Description:
 */
public class GlobalTest {

    public static void main(String[] args) throws Exception{
        try {
            LogManager.getLogManager().reset();
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
            logger.setUseParentHandlers(false);
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new KeyListener());
    }
}

@Data
class Position {
    private int x;
    private int y;
    private String color;

}

class MouseListener implements NativeMouseListener {

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

    }
}


class KeyListener implements NativeKeyListener {

    public boolean is_start = false;

    public final String VK_1 = "1";
    public final String VK_2 = "2";
    public final String VK_3 = "3";
    public final String VK_4 = "4";
    public final String VK_5 = "5";
    public final String VK_6 = "6";
    public final String VK_ALT = "Alt";
    public final String VK_F12 = "F12";

    private Position postion1 = new Position();
    private Robot robot = new Robot();

    KeyListener() throws AWTException {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        String code = NativeKeyEvent.getKeyText(e.getKeyCode());
        System.out.println(code);
        if (VK_1.equals(NativeKeyEvent.getKeyText(e.getKeyCode()))) {
            PointerInfo pinfo = MouseInfo.getPointerInfo();
            Point p = pinfo.getLocation();
            postion1.setX((int) p.getX());
            postion1.setY((int) p.getY());
            Color color = robot.getPixelColor(postion1.getX(), postion1.getY());
            System.out.println(colorToHexValue(color));
        }
        if (VK_F12.equals(code) && !is_start) {
            is_start = true;
            //开启自动任务
            String newColor = colorToHexValue(robot.getPixelColor(postion1.getX(), postion1.getY()));
            //如果坐标点1的值为原来的值，说明未接任务
            if (newColor.equals(postion1.getColor())) {

            }
        }
    }

    private void start() {

    }

    private static String colorToHexValue(Color color) {
        System.out.println(color.getRed());
        System.out.println(color.getGreen());
        System.out.println(color.getBlue());
        return "#" + intToHexValue(color.getRed()) + intToHexValue(color.getGreen()) + intToHexValue(color.getBlue());
    }

    private static String intToHexValue(int number) {
        String result = Integer.toHexString(number & 0xff);
        while (result.length() < 2) {
            result = "0" + result;
        }
        return result.toUpperCase();
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {

    }
}
