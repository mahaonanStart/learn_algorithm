package algorithm.other_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-16 23:34
 * @Description:
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * leetcode 5
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 */
public class LongestPalindromicSubstring {
    private int lo, maxLen;

    /**
     * 中间往两边伸展的思路
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    /**
     * 暴力破解法，依次获取每个字符串，判断是否为回文，取最长的即可
     * 效率比较低
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if ("".equals(s)) return "";
        //回文串的长度
        int len = 0;
        //结果
        String res = null;
        for (int i = 0; i < s.length(); i++) {
            char start = s.charAt(i);
            for (int k = i; k < s.length(); k++) {
                char end = s.charAt(k);
                if (start == end){
                    String str = s.substring(i, k + 1);
                    if (len < str.length() && isPalindromic(str)){
                        res = str;
                        len = str.length();
                    }
                }
            }
        }
        return res;
    }

    /**
     * 暴力破解法的优化版本，长度小于目前已经找好的回文长度的就不用去判断了
     * k = i + len
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        if ("".equals(s)) return "";
        //回文串的长度
        int len = 0;
        //结果
        String res = null;
        for (int i = 0; i < s.length(); i++) {
            char start = s.charAt(i);
            //k的初始位置为i + len，这是因为只需要判断比len长的字符串即可
            for (int k = i + len; k < s.length(); k++) {
                char end = s.charAt(k);
                if (start == end){
                    String str = s.substring(i, k + 1);
                    if (len < str.length() && isPalindromic(str)){
                        res = str;
                        len = str.length();
                    }
                }
            }
        }
        return res;
    }

    /**
     * 代码很简洁，但是会超时，但是看着舒服呀
     * 思路就是先找最长的，从最长长度开始往下找，找到就返回
     * @param s
     * @return
     */
    public String longestPalindrome4(String s) {
        //i为最长回文串的长度，从最长开始找
        for (int i = s.length(); i > 0; i--) {
            for (int k = 0; k < s.length() - i + 1; k++) {
                String str = s.substring(k, k + i);
                if (isPalindromic(str)){
                    return str;
                }
            }

        }
        return "";
    }


    public boolean isPalindromic(String str){
        StringBuilder sb = new StringBuilder(str);
        if (sb.reverse().toString().equals(str)) return true;
        return false;
    }
}
