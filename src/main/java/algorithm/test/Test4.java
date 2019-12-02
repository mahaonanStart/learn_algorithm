package main.java.algorithm.test;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-25 11:53
 * @Description:
 */
public class Test4 {

    public static int pow(int x, int n){
        if (n == 0){
            return 1;
        }
        return x * pow(x, n -1);
    }

    public static void main(String[] args) {
        int pow = pow(2, 5);
        System.out.println(pow);
    }
}
