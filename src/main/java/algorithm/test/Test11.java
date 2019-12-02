package main.java.algorithm.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-16 20:34
 * @Description:
 *
 *
 */
public class Test11 {

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



    public String longestPalindrome5(String s) {
        if ("".equals(s)) return "";
        Map<Character, Integer> map = new HashMap<>();
        //回文串的长度
        int len = 0;
        //结果
        String res = null;
        for (int i = 0; i < s.length(); i++) {
            char ele = s.charAt(i);
            if (map.containsKey(ele)){
                int index = map.get(ele);
                String str = s.substring(index, i + 1);
                if (isPalindromic(str)){
                    if (len < str.length()){
                        res = str;
                        len = str.length();
                    }
                }else{
                    map.put(ele, str.indexOf(ele, index + 1) + index);
                }
            }else{
                map.put(ele, i);
            }
        }
        if (res == null) return String.valueOf(s.charAt(0));
        return res;
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

    public static void main(String[] args) {
        Test11 test = new Test11();
        System.out.println(test.longestPalindrome4(""));
//        System.out.println("123".substring(0, "123".length()));
    }




}
