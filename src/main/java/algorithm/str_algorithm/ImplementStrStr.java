package main.java.algorithm.str_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-29 14:54
 * @Description:
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * leetcode 28
 */
public class ImplementStrStr {

    //abcd      bc
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        //i记录needle第一个元素在haystack中的位置，j用来遍历haystack，k用来遍历needle
        int i = 0, j = 0, k = 0;
        boolean flag = true;
        while (j < haystack.length() && k < needle.length()) {
            if (haystack.charAt(j) == needle.charAt(k)) {
                //记录当前needle第一个元素在haystack中的位置，方便失败后回退
                if (haystack.charAt(j) == needle.charAt(0)) {
                    if (flag) {
                        i = j;
                        flag = false;
                    }
                }
                j ++;
                k ++;
            }else {
                //如果不等，将flag，k归位
                //并且将j设置为i + 1，重新判断
                flag = true;
                k = 0;
                i ++;
                j = i;
            }
            if (k == needle.length()) return i;
            if (j == haystack.length()) return -1;
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }


    public static void main(String[] args) {
        ImplementStrStr im = new ImplementStrStr();
        //"mississippi"
        //"issip"
        System.out.println(im.strStr("mississippi", "issip"));
    }

}
