package algorithm.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-22 22:25
 * @Description:
 * 输入: 123
 * 输出: 321
 *
 * [-124, 123]
 * 12 * 10 120 pop > 4
 */
public class LeetcodeSeven {

    public int reverse(int x) {
        int res = 0;
        while (x != 0){
            int pop = x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)){
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)){
                return 0;
            }
            res = res * 10 + pop;
        }
        return res;
    }
}
