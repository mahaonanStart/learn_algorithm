package simulate.brave;

import javafx.geometry.Pos;
import sun.awt.windows.ThemeReader;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author: M˚Haonan
 * @Date: 2020-01-09 15:02
 * @Description: 自动操作类
 */
public class RobotRun {

    private Position[] positions = new Position[10];

    private Robot robot = new Robot();

    private String fileName = "配置信息(误删).txt";

    private ExecutorService executor = null;




    public RobotRun() throws AWTException {
        robot.setAutoDelay(new Random().nextInt(10));
    }

    public void setPositions(Position[] positions) {
        this.positions = positions;
    }

    public Position parseLine(String str) {
        String[] infos = str.split(":")[1].split(",");
        Position position = new Position();
        position.setX(Integer.parseInt(infos[0].split("=")[1]));
        position.setY(Integer.parseInt(infos[1].split("=")[1]));
        position.setColor(infos[2].split("=")[1]);
        return position;
    }


    public void run() throws InterruptedException {
        executor = Executors.newFixedThreadPool(3);
        //自动打怪设置
        autoFight();
        //接任务设置
        receiveTask();
        //交任务设置
        completeTask();
    }


    public void stop() {
        executor.shutdownNow();
    }

    private Future completeTask() {
        Future<?> completeFuture = executor.submit(() -> {
            while (true) {
                //判断是否需要点击书信
                if (isNotNull(positions[4])) {
                    String currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[4].getX(), positions[4].getY()));
                    if (positions[4].getColor().equals(currColor)) {
                        robot.mouseMove(positions[4].getX(), positions[4].getY());
                        robot.delay(500);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                }
                //判断是否需要对话交任务
                if (isNotNull(positions[5])) {
                    String currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[5].getX(), positions[5].getY()));
                    if (positions[5].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_F);
                    }else {
                        robot.keyRelease(KeyEvent.VK_F);
                    }
                }
                Thread.sleep(100);
            }
        });
        return completeFuture;
    }

    public boolean isNotNull(Position position) {
        return positions != null && !"0".equals(position.getColor());
    }

    private Future receiveTask() {
        Future<?> receiveFuture = executor.submit(() -> {
            while (true) {
                //第一步操作，判断是否需要打开任务栏
                if (isNotNull(positions[1])) {
                    String currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[1].getX(), positions[1].getY()));
                    if (positions[1].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_J);
                        robot.keyRelease(KeyEvent.VK_J);
                    }else {
                        robot.keyRelease(KeyEvent.VK_J);
                    }
                }
                //第二步操作，判断是否移动鼠标到接取任务
                if (isNotNull(positions[2])) {
                    String currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[2].getX(), positions[2].getY()));
                    if (positions[2].getColor().equals(currColor)) {
                        robot.mouseMove(positions[2].getX(), positions[2].getY());
                        robot.delay(500);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                }
                //第三步操作，判断是否需要接任务
                if (isNotNull(positions[3])) {
                    String currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[3].getX(), positions[3].getY()));
                    if (positions[3].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_F);
                    }else {
                        robot.keyRelease(KeyEvent.VK_F);
                    }
                }
                Thread.sleep(100);
            }
        });
        return receiveFuture;
    }

    private Future autoFight() {
        Future<?> fightFuture = executor.submit(() -> {
            while (true) {
                if (isNotNull(positions[0])) {
                    String currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[0].getX(), positions[0].getY()));
                    if (positions[0].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_T);
                    }else {
                        robot.keyRelease(KeyEvent.VK_T);
                    }
                }
                Thread.sleep(100);
            }
        });
        return fightFuture;
    }

}
