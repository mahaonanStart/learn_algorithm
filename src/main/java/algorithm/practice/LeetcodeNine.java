package algorithm.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-22 22:42
 * @Description:
 *
 * 输入: 121
 * 输出: true
 */
public class LeetcodeNine {


    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0) && x != 0) return false;
        int res = 0;
        //12 21˙
        while (x > res){
            res = res * 10 + x % 10;
            x /= 10;
        }
        //x = 12 res = 123
        return (x == res) || (x == res / 10);
    }
}
