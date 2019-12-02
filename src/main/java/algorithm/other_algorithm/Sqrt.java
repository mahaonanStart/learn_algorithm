package main.java.algorithm.other_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-06 12:27
 * @Description: Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 *
 * 求一个非负整数的平方根，小数部分被舍去
 *
 * leetcode 69
 */
public class Sqrt {

    /**
     * 二分法完成
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        long begin = 0;
        long end = x;
        long mid = (begin + end) / 2;
        while (true){
            if (mid * mid <= x && (mid + 1) * (mid + 1) > x) break;
            if (mid * mid > x){
                end = mid - 1;
            }
            if (mid * mid < x){
                begin = mid + 1;
            }
            mid = (begin + end) / 2;
        }
        return (int)mid;
    }

    /**
     * 求精确值
     * @param x
     * @return
     */
    public double mySqrt2(int x){
        double begin = 0;
        double end = x;
        double mid = (begin + end) / 2;
        while (true){
            if (mid * mid == x) break;
            if (end - begin < Math.pow(10, -9)) break;
            if (mid * mid > x){
                end = mid;
            }
            if (mid * mid < x){
                begin = mid;
            }
            mid = (begin + end) / 2;
        }
        return mid;
    }


    /**
     * 牛顿迭代法
     * @param x
     * @return
     */
    public double mySqrt3(int x){
        double r = x;
        while (r * r > x){
            r = (r + x / r) / 2;
        }
        return r;
    }


    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        int i = sqrt.mySqrt(
                100);
        double j = sqrt.mySqrt3(4);
        System.out.println(j);
    }
}
