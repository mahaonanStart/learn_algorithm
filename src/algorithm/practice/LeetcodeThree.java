package algorithm.practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-22 18:59
 * @Description:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class LeetcodeThree {

    /**
     * 暴力破解
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (!set.add(s.charAt(j))){
                    break;
                }
            }
            max = Math.max(max, set.size());
        }
        return max;
    }

    /**
     * 滑动窗口
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, k = 0; i < s.length(); i++) {
            char ele = s.charAt(i);
            if (map.containsKey(ele)){
                k = Math.max(k, map.get(ele) + 1);
            }
            map.put(ele, i);
            max = Math.max(max, i - k + 1);
        }
        return max;
    }


}
