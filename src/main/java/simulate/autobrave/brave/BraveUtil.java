package simulate.autobrave.brave;

import tools.support.concurrent.NamedThreadFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author: M˚Haonan
 * @Date: 2020-01-08 18:29
 * @Description: 工具类，封装用到的工具方法
 */
public class BraveUtil {


    public static ScheduledThreadPoolExecutor newScheduledThreadPool(String name, int coreSize) {
        return new ScheduledThreadPoolExecutor(coreSize, new NamedThreadFactory(name));
    }

    public static boolean isNumber(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return str.matches("[0-9]+");
    }


    public static boolean isSingleNumber(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return str.matches("[0-9]");
    }

    public static String colorToHexValue(Color color) {
        return "#" + intToHexValue(color.getRed()) + intToHexValue(color.getGreen()) + intToHexValue(color.getBlue());
    }

    public static String intToHexValue(int number) {
        String result = Integer.toHexString(number & 0xff);
        while (result.length() < 2) {
            result = "0" + result;
        }
        return result.toUpperCase();
    }


    public static void download(BufferedImage bi, String fileName) {
        File outputfile = new File(fileName);
        try {
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isBlack(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static int singleStrToAscii(String str) {
        if ("tab".equalsIgnoreCase(str)) {
            return 9;
        }
        return str.toCharArray()[0];
    }

}
