package main.java.algorithm.test;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-28 12:58
 * @Description:
 */
public class Test6 {

    public static void main(String[] args) {
        System.out.println(count(13));
    }

    private static int count(int n) {
        int total = 0;
        for (int i = 1; i <= n; i++) {
           total += index(i);
        }
        return total;
    }

    private static int index(int n) {
        return 0;
    }
}
