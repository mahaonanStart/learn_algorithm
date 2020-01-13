package simulate.autobrave.autoFight;

import simulate.autobrave.brave2.BraveUtil;
import simulate.autobrave.brave2.Position;

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

    public boolean isNotNull(Position position) {
        return positions != null && !"0".equals(position.getColor());
    }

    public static void main(String[] args) throws Exception{
        RobotRun run = new RobotRun();
        run.run();
    }

}
