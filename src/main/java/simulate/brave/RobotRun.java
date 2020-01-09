package simulate.brave;

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

    private ExecutorService executor = Executors.newFixedThreadPool(5);




    public RobotRun() throws AWTException {
        init();
        robot.setAutoDelay(new Random().nextInt(10));
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
        //自动打怪设置
        if (isSetFight()) {
            Future future = autoFight();
            Thread.sleep(5000);
            future.cancel(true);
            executor.shutdown();
        }
        //接任务设置
        receiveTask();

    }

    public boolean isNotNull(Position position) {
        return positions != null && !"0".equals(position.getColor());
    }

    private Future receiveTask() {
        return null;
    }

    public void stop() {

    }

    public boolean isSetFight() {
        return positions[0] != null && !"0".equals(positions[0].getColor());
    }

    private Future autoFight() {
        Future<?> fightFuture = executor.submit(() -> {
            while (true) {
                String currColor = BraveUtil.colorToHexValue(robot.getPixelColor(positions[0].getX(), positions[0].getY()));
                System.out.println("currColor = " + currColor);
                System.out.println("oldColor = " + positions[0].getColor());
                if (!positions[0].getColor().equals(currColor)) {
                    robot.keyPress(KeyEvent.VK_T);
                }else {
                    robot.keyRelease(KeyEvent.VK_T);
                }
                Thread.sleep(100);
            }
        });
        return fightFuture;
    }



    public static void main(String[] args) throws Exception {
        RobotRun robotRun = new RobotRun();
        robotRun.run();
    }

}
