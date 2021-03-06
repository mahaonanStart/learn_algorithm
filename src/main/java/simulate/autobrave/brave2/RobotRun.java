package simulate.autobrave.brave2;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        init();
        robot.setAutoDelay(new Random().nextInt(10));
    }

    public void setPositions(Position[] positions) {
        this.positions = positions;
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


    public void run() throws InterruptedException {
        executor = Executors.newFixedThreadPool(4);
        //自动打怪设置
        autoFight();
        //接任务设置
        receiveTask();
        //交任务设置
        completeTask();
        //自动捡东西和修武器
        fixWeapon();
    }

    private Future fixWeapon() {
        Future<?> fixFuture = executor.submit(() -> {
            String currColor = null;
            while (true) {
                //判断是否需要捡东西
                if (isNotNull(positions[7])) {
                    currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[7].getX(), positions[7].getY()));
                    if (positions[7].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_F);
                        robot.keyRelease(KeyEvent.VK_F);
                    }else {
                        robot.keyRelease(KeyEvent.VK_F);
                    }
                }
                Thread.sleep(100);
                //判断是否需要修武器
                if (isNotNull(positions[8])) {
                    currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[8].getX(), positions[8].getY()));
                    if (positions[8].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_9);
                        robot.keyRelease(KeyEvent.VK_9);
                    }else {
                        robot.keyRelease(KeyEvent.VK_9);
                    }
                }
                Thread.sleep(100);
            }
        });
        return fixFuture;
    }


    public void stop() {
        executor.shutdownNow();
    }

    private Future completeTask() {
        Future<?> completeFuture = executor.submit(() -> {
            String currColor = null;
            while (true) {
                //判断是否需要点击书信
                if (isNotNull(positions[5])) {
                    currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[5].getX(), positions[5].getY()));
                    if (positions[5].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_O);
                        robot.keyRelease(KeyEvent.VK_O);
                        Thread.sleep(300);
                        robot.mouseMove(positions[5].getX(), positions[5].getY());
                        robot.delay(100);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                    Thread.sleep(100);
                }
                //判断是否需要对话交任务
                if (isNotNull(positions[6])) {
                    currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[6].getX(), positions[6].getY()));
                    if (positions[6].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_F);
                        robot.delay(200);
                        robot.keyRelease(KeyEvent.VK_F);
                    }else {
                        robot.keyRelease(KeyEvent.VK_F);
                    }
                }
                Thread.sleep(100);
            }
        });
        return completeFuture;
    }

    private Future receiveTask() {
        Future<?> receiveFuture = executor.submit(() -> {
            String currColor = null;
            while (true) {
                //第一步操作，判断是否需要打开任务栏
                if (isNotNull(positions[1])) {
                    currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[1].getX(), positions[1].getY()));
                    if (positions[1].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_J);
                        robot.keyRelease(KeyEvent.VK_J);
                    }else {
                        robot.keyRelease(KeyEvent.VK_J);
                    }
                }
                //第二步，移动到橙色点击, 并移动到书信点击
                if (isNotNull(positions[9])) {
                    currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[9].getX(), positions[9].getY()));
                    if (positions[9].getColor().equals(currColor)) {
                        robot.mouseMove(positions[2].getX(), positions[2].getY());
                        robot.delay(500);
                        clickDoubleLeftMouse();
                        robot.delay(500);
                        robot.mouseMove(positions[3].getX(), positions[3].getY());
                        robot.delay(500);
                        clickDoubleLeftMouse();
                    }
                }
                Thread.sleep(100);
                //第三步操作，判断是否需要接任务
                if (isNotNull(positions[4])) {
                    currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[4].getX(), positions[4].getY()));
                    if (positions[4].getColor().equals(currColor)) {
                        robot.keyPress(KeyEvent.VK_F);
                        robot.delay(200);
                        robot.keyRelease(KeyEvent.VK_F);
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
            String currColor = null;
            while (true) {
                if (isNotNull(positions[0])) {
                    currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[0].getX(), positions[0].getY()));
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


    private void clickDoubleLeftMouse() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public boolean isNotNull(Position position) {
        return positions != null && !"0".equals(position.getColor());
    }

    public static void main(String[] args) throws Exception{
        RobotRun run = new RobotRun();
        run.run();
    }

}
