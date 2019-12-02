package algorithm.practice;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-23 16:02
 * @Description:
 */
public class Leetcode17 {

    private Map<Character, List<Character>> map = new HashMap<>();
    public Leetcode17(){
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    //"2345"
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        letterCombinations(res, digits, 0, new StringBuilder());
        return res;
    }

    private void letterCombinations(List<String> res, String digits, int index, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
        }else {
            List<Character> list = map.get(digits.charAt(index));
            for (int i = 0; i < list.size(); i++) {
                StringBuilder s = new StringBuilder(sb);
                letterCombinations(res, digits, index + 1, s.append(list.get(i)));
            }
        }
    }

    public static void main(String[] args) {
        Leetcode17 leetcode17 = new Leetcode17();
        System.out.println(leetcode17.letterCombinations("23"));
    }
}
