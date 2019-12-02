package algorithm.recursion;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-22 16:38
 * @Description:
 *Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * leetcode 17
 */
public class LetterCombinationsofAPhoneNumber {
    private Map<Character, List<Character>> map = new HashMap<>();
    public LetterCombinationsofAPhoneNumber(){
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    /**
     * 递归解法
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        letterCombinations(res, digits, 0, new StringBuilder());
        return res;
    }

    private void letterCombinations(List<String> res, String digits, int start, StringBuilder s) {
        if (start >= digits.length()){
            res.add(s.toString());
        }else{
            List<Character> list = map.get(digits.charAt(start));
            for (int i = 0; i < list.size(); i++) {
                //这里必须新new一个StringBuilder，不然每次都是同一个对象，会出错
                StringBuilder sb = new StringBuilder(s);
                letterCombinations(res, digits, start + 1, sb.append(list.get(i)));
            }
        }
    }

    /**
     * 递归解法2
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        if ("".equals(digits)) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        letterCombinations(res, new StringBuilder(), digits);
        return res;
    }

    private void letterCombinations(List<String> res, StringBuilder com, String next) {
        if (next.length() == 0){
            res.add(com.toString());
        }else {
            //截取剩余字符串中的第一个
            char digit = next.substring(0, 1).toCharArray()[0];
            List<Character> list = map.get(digit);
            for (int i = 0; i < list.size(); i++) {
                StringBuilder sb = new StringBuilder(com);
                letterCombinations(res, sb.append(list.get(i)), next.substring(1));
            }
        }

    }


    public static void main(String[] args) {
        LetterCombinationsofAPhoneNumber lc = new LetterCombinationsofAPhoneNumber();
        List<String> list = lc.letterCombinations2("23");
        System.out.println(list);
    }
}
