package algorithm.str_algorithm;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-30 14:30
 * @Description:
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 *
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * leetcode 30
 */
public class SubstringWithConcatenationOfAllWords {

    /**
     * 两个map统计数量进行比对
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        if (s.isEmpty() || words.length == 0) return new ArrayList<>();
        int wordNum = words.length;
        int wordLen = words[0].length();
        List<Integer> res = new ArrayList<>();
        //设计一个HashMap，统计words中各单词的数量
        Map<String, Integer> map1 = new HashMap<>();
        for (String word : words) {
            map1.merge(word, 1, (x, y) -> x + y);
        }
        //遍历s
        for (int i = 0; i < s.length() - wordLen * wordNum + 1; i++) {
            //设计一个map，统计当前子字符串中words中单词出现的次数
            Map<String, Integer> map2 = new HashMap<>();
            int num = 0;
            while (num < wordNum) {
                String str = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                //如果该单词在words中
                if (map1.containsKey(str)) {
                    map2.merge(str, 1, (x, y) -> x + y);
                    //如果当前字符串中的某个单词出现次数大于总个数，说明该子字符串不满足条件
                    if (map2.get(str) > map1.get(str)) {
                        break;
                    }
                    //如果当前子字符串中某个单词出现的次数小于总的个数，则继续下一个判断
                //如果不在words中，直接跳过本次，判断下一个
                }else {
                    break;
                }
                num ++;
            }
            //如果循环是正常退出，即遍历完,则找到了符合条件的字符串
            if (num == wordNum) {
                res.add(i);
            }
        }
        return res;
    }


    public List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < one_word; i++) {
            int left = i, right = i, count = 0;
            HashMap<String, Integer> tmp_map = new HashMap<>();
            while (right + one_word <= s.length()) {
                String w = s.substring(right, right + one_word);
                right += one_word;
                if (!map.containsKey(w)) {
                    count = 0;
                    left = right;
                    tmp_map.clear();
                } else {
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    count++;
                    while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                        String t_w = s.substring(left, left + one_word);
                        count--;
                        tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                        left += one_word;
                    }
                    if (count == word_num) res.add(left);
                }
            }
        }
        return res;
    }

    /**
     * 会超时，但思路正确
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        Arrays.sort(words);
        if (s.isEmpty() || words.length == 0) return new ArrayList<>();
        Set<String> set = new HashSet<>();
        addStr(set, Arrays.asList(words), "");
        System.out.println(set);
        List<Integer> list = new ArrayList<>();
        int totalLen = words[0].length() * words.length;
        for (int i = 0; i < s.length() - totalLen; i ++) {
            String str = s.substring(i, i + totalLen);
            if (set.contains(str)) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 将数组中单词所有全组合拼接起来的算法
     * 效率比较低，会超时
     * @param set
     * @param list
     * @param string
     */
    private void addStr(Set<String> set, List<String> list, String string) {
        if (list.isEmpty()) {
            set.add(string);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i).equals(list.get(i - 1))) continue;
            List<String> temp = new ArrayList<>(list);
            addStr(set, temp, string + temp.remove(i));
        }
    }


    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords sw = new SubstringWithConcatenationOfAllWords();
        List<Integer> substring = sw.findSubstring("barfoothefoobarman", new String[]{"dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty"});
        System.out.println(substring);
    }
}
