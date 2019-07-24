package algorithm.recursion;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-23 10:16
 * @Description:
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 *
 * 注意：整数顺序将表示为一个字符串。
 *
 * leetcode 38
 */
public class CountAndSay {

    /**
     * 递归算法
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String prev = countAndSay(n - 1);
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < prev.length(); i++) {
            if (prev.charAt(i) == prev.charAt(i - 1)) {
                count ++;
            }else {
                sb.append(String.valueOf(count)).append(prev.charAt(i - 1));
                count = 1;
            }
        }
        //循环结束后，最后一位没有处理
        sb.append(String.valueOf(count)).append(prev.charAt(prev.length() - 1));
        return sb.toString();
    }

    /**
     * 循环迭代
     * @param n
     * @return
     */
    public String countAndSay2(int n) {
        if (n == 1) return "1";
        String prev = "1";
        //从n=2开始一直到n=n
        for (int i = 2; i <= n; i++) {
            int count = 1;
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < prev.length(); j++) {
                if (prev.charAt(j) == prev.charAt(j - 1)) {
                    count ++;
                }else {
                    sb.append(String.valueOf(count)).append(prev.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(String.valueOf(count)).append(prev.charAt(prev.length() - 1));
            prev = sb.toString();
        }
        return prev;
    }

    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay2(5));
    }
}
