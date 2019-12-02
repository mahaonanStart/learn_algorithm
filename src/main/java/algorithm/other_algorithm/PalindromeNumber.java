package algorithm.other_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-17 22:37
 * @Description:
 *Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 *
 * leetcode 9
 *
 * 回文数判断
 */
public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int rev = 0;
        int curr = x;
        while (curr != 0){
            int pop = curr % 10;
            curr /= 10;
            rev = rev * 10 + pop;
        }
        if (rev == x) return true;
        return false;
    }

    public boolean isPalindrome2(int x) {
        //x为负数直接false，还有一种情况是个位数为0，且这个数不是0，也返回false
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int rev = 0;
        //只需取一半就可以了
        while (x > rev){
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        //如果x为偶数，那么如果是回文数，x和rev一定相等
        //如果x为奇数，那么可以利用rev/10将中间数消掉，即12321，while循环后
        //由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        //x=12, rev = 123, 可以把中间的数消掉而不影响结果
        return x == rev || x == rev / 10;

    }
}
