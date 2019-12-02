package main.java.algorithm.hash_collection;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-12 21:07
 * @Description: Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
*   leetcode 3
 *
 *   给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * set解决
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int max = 0;
        //依次从开始位置遍历，每个字母作为子序列的开始去试探
        for (int i = 0; i < s.length(); i++) {
            //定义一个set用于记录出现重复元素
            Set<Character> set = new HashSet<>();
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                //如果出现重复元素，就停止循环，这样set里面存放的就是本次不重复的所有连续子序列字母
                if (!set.add(s.charAt(j))) break;
            }
            //将最长子序列长度保存在max中
            if (set.size() > max) {
                max = set.size();
            }
        }
        return max;
    }

    /**
     * Map解决
     * 也是一种滑动窗口的思想
     * 只不过把下标记录了下来，可以一步到位，不像set一样，一个个的移动
     * @param s
     * @return
     */
    public int lengthOf(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        //定义两个指针
        for (int i = 0, k = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                //如果当前序列出现重复元素，将k移到上次重复元素的右侧，由于指针只能向右移动，因此这里取较大的一个
                k = Math.max(k, map.get(s.charAt(i)) + 1);
            }
            //将当前字母和下标放入map中记录下来
            map.put(s.charAt(i), i);
            //i和k始终保持为一个不重复子序列的开始和结束位置
            //i为当前遍历到的位置（结束位置），k为开始位置
            max = Math.max(max, i - k + 1);
        }
        return max;
    }


    /**
     * 类似于滑动窗口一样的思维
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     * int[128] for ASCII
     * int[256] for Extended ASCII
     *
     * 其实和map一样的思路，只不过把元素下标的保存放到了数组中
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        //存放当前元素的下标
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            //i是起始位置，是上一重复的元素的右侧位置, 由于下面put的时候已经+1了，因此这里不需要了
            //并且也保证了i的值不会减少，相当于指针是一直往右移动的
            //这一步就相当于map中计算k的值
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            //这一步就相当于map中put操作
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));
    }
}
