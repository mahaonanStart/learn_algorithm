package main.java.algorithm.str_algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-29 18:09
 * @Description:
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 *
 * leetcode 58
 *
 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        if (s.length() == 0) return 0;
        String trim = s.trim();
        String[] arr = s.split(" ");
        if (arr.length == 0) return 0;
        System.out.println(arr.length);
        if (" ".equals(arr[arr.length - 1])) {
            return 0;
        }else {
            return arr[arr.length - 1].length();
        }
    }

    public int lengthOfLastWord2(String s) {
        if (s.length() == 0) return 0;
        List<Character> list = new ArrayList<>();
        String trim = s.trim();
        for (int i = 0; i < trim.length(); i++) {
            Character c = trim.charAt(i);
            if (' ' == c) {
                list.clear();
            }else {
                list.add(c);
            }
        }
        return list.size();
    }

    public int lengthOfLastWord3(String s) {
        int end = s.length() - 1;
        while(end >= 0 && s.charAt(end) == ' ') end--;
        if(end < 0) return 0;
        int start = end;
        while(start >= 0 && s.charAt(start) != ' ') start--;
        return end - start;
    }

    public static void main(String[] args) {
        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();
        int i = lengthOfLastWord.lengthOfLastWord2("a ");
        System.out.println(i);

    }
}
