package main.java.algorithm.recursion;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-30 15:00
 * @Description: 计算x的n次方
 */
public class Pow {

    /**
     * 利用库函数
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        return Math.pow(x,n);
    }

    /**
     * 暴力破解
     * @param x
     * @param n
     * @return
     */
    public static double myPow2(double x, int n) {
        if (n < 0){
            n = - n;
            x = 1/x;
        }
        if (n == 0) return 1;
        if (n == 1) return x;
        return x * myPow2(x, (n - 1));
    }

    public static double myPow3(double x, int n) {
        if (n < 0){
           return 1/myPow3(x, -n);
        }
        if (n == 0) return 1;
        if (n == 1) return x;
        return (n % 2) == 0 ? myPow3(x * x, n / 2) : x * myPow3(x*x, n / 2);
    }


    /**
     * 利用位运算, 如果n为奇数，先乘一次再x，然后再x^2，n减半，因为n直接除以2会消灭掉一个x
     * @param x
     * @param n
     * @return
     */
    public static double myPow4(double x, int n) {
        if (n == 0) return 1;
        if (n < 0 ){
            x = 1 / x;
            n = - n;
        }
        double pow = 1;
        while (n > 0){
            ///如果n为奇数
            if ((n & 1) == 1){
                pow *= x;
            }
            x *= x;
            n >>= 1;
        }
        return pow;
    }

    public static void main(String[] args) {
        double v = myPow3(5, 2);
        System.out.println(v);

    }

}
