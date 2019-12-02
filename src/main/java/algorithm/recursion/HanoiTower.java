package main.java.algorithm.recursion;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-24 17:20
 * @Description: 汉诺塔递归求解
 */
public class HanoiTower {

    /**
     * 将n个汉诺塔从start移动到end
     * @param n
     * @param start
     * @param end
     */
    public static void move(int n, String start,String middle, String end){
        if (n == 1){
            System.out.println("将第1个从" + start + "移动到" + end);
            return;
        }
        move(n - 1, start, end, middle);
        System.out.println("将第" + n + "个从" + start + "移动到" + end);
        move(n - 1, middle, start, end);
    }

    public static void main(String[] args) {
        move(3, "A", "B", "C");
    }
}
