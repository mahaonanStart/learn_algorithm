package main.java.algorithm.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-22 21:43
 * @Description:
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 *
 * a b c b a
 * 0 1 2 3 4
 * abba
 *
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 */
public class LeetcodeFour {


    private int lo, len;

    public String longestPalindrome2(String s) {
        if (s.length() < 2) return s;
        for (int i = 0; i < s.length() - 1; i++) {
            paline(s, i, i);
            paline(s, i, i + 1);
        }
        return s.substring(lo, lo + len);
    }

    private void paline(String s, int i, int j) {
        while (i >= 0 && j <= s.length() - 1 && s.charAt(i) == s.charAt(j)){
            i --;
            j ++;
        }
        if (len < j - i - 1){
            lo = i + 1;
            len = j - i - 1;
        }
    }

    /**
     * 暴力解法
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if ("".equals(s)) return "";
        int max = 0;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char start = s.charAt(i);
            for (int j = i + max; j < s.length(); j++) {
                char end = s.charAt(j);
                if (start == end){
                    String str = s.substring(i, j + 1);
                    if (isPalindrome(str)){
                        res = str;
                        max = str.length();
                    }
                }
            }
        }
        return res;
    }


    private boolean isPalindrome(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (sb.reverse().toString().equals(str)) return true;
        return false;
    }
}
