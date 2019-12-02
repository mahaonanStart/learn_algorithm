package main.java.algorithm.hash_collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-16 20:34
 * @Description: Roman to Integer
 *
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * leetcode 13
 */
public class RomanToInt{

    public static int romanToInt(String s) {
        //最后结果
        int num = 0;
        //预先存放各值
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", -2);
        map.put("IX", -2);
        map.put("XL", -20);
        map.put("XC", -20);
        map.put("CD", -200);
        map.put("CM", -200);
        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            num += map.get(String.valueOf(s.charAt(i)));
            if (i > 0){
                StringBuilder sb = new StringBuilder();
                sb.append(s.charAt(i - 1)).append(s.charAt(i));
                Integer ele = map.get(sb.toString());
                if (ele != null){
                    num += ele;
                }
            }
        }
        return num;
    }

    public static int romanIoInt2(String s){
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    res += (res >= 5 ? -1 : 1);
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    res += 10 * (res >= 50 ? -1 : 1);
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    res += 100 * (res >= 500 ? -1 : 1);
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

}

