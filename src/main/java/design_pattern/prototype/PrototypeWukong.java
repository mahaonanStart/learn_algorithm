package design_pattern.prototype;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-10 10:42
 * @Description: 孙悟空利用原型模式复制自己
 */
class SunWukong extends JPanel implements Cloneable {

    public SunWukong() {
        JLabel l1 = new JLabel(new ImageIcon("Wukong.jpg"));
        this.add(l1);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SunWukong w = null;
        try {
            w = (SunWukong)super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.out.println("拷贝悟空失败!");
        }
        return w;
    }
}
public class PrototypeWukong {

    public static void main(String[] args) throws CloneNotSupportedException {
        JFrame jf = new JFrame("原型模式测试");
        jf.setLayout(new GridLayout(1,2));
        Container contentPane = jf.getContentPane();
        SunWukong obj1 = new SunWukong();
        contentPane.add(obj1);
        SunWukong obj2 = (SunWukong)obj1.clone();
        contentPane.add(obj2);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
