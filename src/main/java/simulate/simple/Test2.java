import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Test2 {
    static boolean ifRun = true;
    static JButton startBt;
    static JButton endBt;


    public static void main(String[] args) {
        // 显示主界面
        showMain();

    }

    static Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            // 模拟移动到当前鼠标位置
            while (ifRun) {
                Point point = MouseInfo.getPointerInfo().getLocation();
                System.out.println("x=" + point.getX() + ",y=" + point.getY());
                /* robot.mouseMove((int)1829.0, (int) 988.0);
                robot.mouseMove((int) point.getX(), (int) point.getY());/*
                // 模拟鼠标按下左键
                robot.mousePress(InputEvent.BUTTON1_MASK);
                // 模拟鼠标松开左键
                robot.mouseRelease(InputEvent.BUTTON1_MASK);/**/
                // 模拟鼠标按下右键
                /* robot.mousePress(InputEvent.BUTTON3_MASK);
                // 模拟鼠标松开右键
                robot.mouseRelease(InputEvent.BUTTON3_MASK); */

                try {
                    thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    });

    static public void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "提示信息",
                JOptionPane.PLAIN_MESSAGE);
    }

    static public void showMain() {
        JDialog dialog = new JDialog();
        // 设置大小
        dialog.setSize(200, 100);
        // 设置标题
        dialog.setTitle("界面");

        startBt = new JButton("开始");
        endBt = new JButton("结束");
        // 绑定监听
        startBt.addActionListener(actionListener);
        endBt.addActionListener(actionListener);
        startBt.setBounds(35, 10, 60, 40);
        endBt.setBounds(90, 10, 60, 40);
        // 设置布局为空，使用坐标控制控件位置的时候，一定要设置布局为空
        dialog.setLayout(null);
        // 添加控件
        dialog.add(startBt);
        dialog.add(endBt);
        // 设置dislog的相对位置，参数为null，即显示在屏幕中间
        dialog.setLocationRelativeTo(null);
        // 设置当用户在此对话框上启动 "close" 时默认执行的操作
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // 设置是否显示
        dialog.setVisible(true);
    }

    static ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == startBt) {
                showMsg("已开始——————请在三秒内点击");
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        showMsg("已开始获取鼠标位置并已启动线程");
                        thread1.start();
                    }
                }, 3000);

            }

            if (e.getSource() == endBt) {
                ifRun = false;
                showMsg("结束");
            }
        }
    };

    static public void sss() throws AWTException{
        Robot robot = new Robot();
        Point point = MouseInfo.getPointerInfo().getLocation();


        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        //鼠标按下扭曲丛林坐标
        point.setLocation(740.0,408.0);
        //鼠标按下扭曲丛林确认坐标
        point.setLocation(893.0,872.0);
        //鼠标按下寻找对局
        point.setLocation(893.0,872.0);
    }
    static Thread thread1 = new Thread(new Runnable() {

        @Override
        public void run() {
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            int count = 0;
            boolean isFirend = false;
            boolean isFirend1 = false;
            boolean isFirend2 = false;
            // 模拟移动到当前鼠标位置
            while (ifRun) {
                Point point = MouseInfo.getPointerInfo().getLocation();
                robot.mouseMove((int) point.getX(), (int) point.getY());
                System.out.println("已经开始-"+count*3+"-秒");
                //在客户端15秒开始游戏
                if(count==5){
                    //鼠标15秒后按下play坐标
                    robot.mouseMove((int)458.0,(int)226.0);
                }else if(count == 6){
                    //鼠标按下扭曲丛林坐标
                    robot.mouseMove((int)740.0,(int)408.0);
                }else if(count == 7){
                    //鼠标按下扭曲丛林确认坐标
                    robot.mouseMove((int)893.0,(int)872.0);
                }else if(count == 8){
                    //鼠标按下寻找对局
                    robot.mouseMove((int)893.0,(int)872.0);//如果是低于优先级队列
                }else if(count > 9 && !isFirend){
                    //匹配到好友接受请求
                    isFirend = true;
                    robot.mouseMove((int)992.0,(int)731.0);
                }else if(isFirend&&!isFirend2){
                    //选择英雄
                    isFirend = false;
                    robot.mouseMove((int)730.0,(int)327.0);
                }if(count>150&&count<600&&!isFirend2){
                    //游戏开始，移动英雄
                    int x = count%2 == 0 ? (int) 1829.0 : (int) 988.0;
                    int y = count%2 == 0 ? (int) 988.0 : (int) 1829.0;
                    robot.mouseMove(x,y);
                    //模拟鼠标按下右键
                    robot.mousePress(InputEvent.BUTTON3_MASK);
                    // 模拟鼠标松开右键
                    robot.mouseRelease(InputEvent.BUTTON3_MASK);
                    try {
                        thread1.sleep(3000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    count++;
                    continue;
                }else if(count>600&&!isFirend1){//游戏结束
                    isFirend2 = true;
                    isFirend1 = true;
                    //赞玩家
                    robot.mouseMove((int)992.0,(int)828.0);
                }else if(isFirend1){
                    isFirend = false;
                    isFirend1 = false;
                    isFirend2 = false;
                    count = 0;
                    //
                    robot.mouseMove((int)898.0,(int)861.0);
                }
                //赞玩家898.0,861.0
                //再玩一次898.0,861.0
                // 模拟鼠标按下左键
                robot.mousePress(InputEvent.BUTTON1_MASK);
                // 模拟鼠标松开左键
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                // 模拟鼠标按下右键
                /*robot.mousePress(InputEvent.BUTTON3_MASK);
                // 模拟鼠标松开右键
                robot.mouseRelease(InputEvent.BUTTON3_MASK);*/
                try {
                    count++;
                    thread1.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    });

}