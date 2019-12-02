package main.java.design_pattern.decorator.practice;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 14:44
 * @Description: 原身
 */
public class Original extends JFrame implements Morrigan{

    private static final long serialVersionUID = 1L;

    private String t="Morrigan0.jpg";

    public Original() {
        super("《恶魔战士》中的莫莉卡·安斯兰");
    }

    public void setImage(String t) {
        this.t = t;
    }

    @Override
    public void display() {
        this.setLayout(new FlowLayout());
        JLabel l1 = new JLabel(new ImageIcon(t));
        this.add(l1);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}
