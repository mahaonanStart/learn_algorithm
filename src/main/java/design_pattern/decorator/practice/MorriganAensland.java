package main.java.design_pattern.decorator.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 14:52
 * @Description:
 */
public class MorriganAensland {

    public static void main(String[] args) {
        Morrigan m0 = new Original();
        m0.display();
        Morrigan m1 = new Succubus(m0);
        m1.display();
    }
}
