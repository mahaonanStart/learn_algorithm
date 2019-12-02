package main.java.algorithm.hash_collection;

import java.util.Arrays;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-24 10:07
 * @Description: Given two strings s and t , write a function to determine if t is an anagram of s.
 *  判断两个字符串是否为相同字母异序词
 */
public class ValidAnagram {

    /**
     * hash的一种巧妙算法
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        int [] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a'] ++;
            counter[t.charAt(i) - 'a'] --;
        }
        for (int i : counter) {
            if (i != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 利用排序做
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        char [] str1 = s.toCharArray();
        char [] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    public static void main(String[] args) {
        ValidAnagram va = new ValidAnagram();
        String s = "act";
        String t = "cat";
        System.out.println(va.isAnagram2(s, t));

    }
}
