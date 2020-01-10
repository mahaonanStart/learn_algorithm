package simulate.brave;

import javafx.geometry.Pos;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import simulate.brave.BraveUtil;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * @Author: M˚Haonan
 * @Date: 2020-01-09 14:54
 * @Description: 键盘监听类
 * 主要功能是根据用户键盘输入指定键位，实现相应的功能
 */
public class AutoBrave implements NativeKeyListener {


    private Position[] positions = new Position[10];

    private Robot robot = new Robot();

    private String fileName = "配置信息(误删).txt";

    private boolean isStart = false;

    private RobotRun robotRun = new RobotRun();

    public AutoBrave() throws AWTException {
        init();
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        String code = NativeKeyEvent.getKeyText(e.getKeyCode());
        setPoint(code);
    }

    private void setPoint(String code) {
//        System.out.println(code);
        if (BraveUtil.isSingleNumber(code)) {
            PointerInfo pinfo = MouseInfo.getPointerInfo();
            Point p = pinfo.getLocation();
            String color = BraveUtil.colorToHexValue(getPostionColor((int) p.getX(), (int) p.getY()));
            positions[Integer.parseInt(code)] = new Position((int) p.getX(), (int) p.getY(), color);
        } else if ("]".equalsIgnoreCase(code)) {
            print();
            printCurrent();
        } else if (SystemConstant.VK_F11.equalsIgnoreCase(code)) {
            //终止程序并将位置信息写入文件
            write();
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (Exception e) {
                System.exit(1);
            }
        } else if (SystemConstant.VK_F12.equalsIgnoreCase(code)) {
            //开启自动任务
            if (!isStart) {
                isStart = true;
                robotRun.setPositions(this.positions);
                try {
                    robotRun.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                isStart = false;
                robotRun.stop();
            }
        }
    }

    private void write() {
        FileWriter writer;
        try {
            writer = new FileWriter(this.fileName);
            writer.write("");//清空原文件内容
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < positions.length; i++) {
                Position p = positions[i];
                if (p != null) {
                    sb.append("p").append(i).append(":x=").append(p.getX()).append(",y=").append(p.getY()).append(",color=").append(p.getColor()).append("\n");
                }else {
                    sb.append("p").append(i).append(":x=0,y=0,color=0\n");
                }
            }
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Color getPostionColor(int x, int y) {
        return robot.getPixelColor(x, y);
    }

    public void print() {
        for (Position position : positions) {
            if (position != null) {
                System.out.println(position);
            }
        }
    }

    public void printCurrent() {
        for (Position position : positions) {
            if (position != null) {
                System.out.println(BraveUtil.colorToHexValue(getPostionColor(position.getX(), position.getY())));
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }


    public void init() {
        FileReader fr = null;
        BufferedReader bf = null;
        try {
            fr = new FileReader(this.fileName);
            bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            int i = 0;
            while ((str = bf.readLine()) != null && !"".equals(str)) {
                positions[i] = parseLine(str);
                i ++;
            }
            print();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bf.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Position parseLine(String str) {
        String[] infos = str.split(":")[1].split(",");
        Position position = new Position();
        position.setX(Integer.parseInt(infos[0].split("=")[1]));
        position.setY(Integer.parseInt(infos[1].split("=")[1]));
        position.setColor(infos[2].split("=")[1]);
        return position;
    }


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
        GlobalScreen.addNativeKeyListener(new AutoBrave());
    }

}
