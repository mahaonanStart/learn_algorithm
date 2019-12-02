package main.java.algorithm.recursion;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-20 23:00
 * @Description:
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 *
 * leetcode 14
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class LongestCommonPrefix {
    /**
     * 直接解决，每两个一组寻找公共前缀，然后迭代进行
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        String prev = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String curr = strs[i];
            int max = Math.min(curr.length(), prev.length());
            int j = 0;
            StringBuilder sb = new StringBuilder();
            while (j < max){
                //第一个比对失败，没有公共前缀，直接返回false
                if (j == 0 && curr.charAt(j) != prev.charAt(j)){
                    return "";
                }
                if (curr.charAt(j) == prev.charAt(j)){
                    sb.append(curr.charAt(j));
                    j ++;
                }else{
                    break;
                }
            }
            prev = sb.toString();
        }
        return prev;
    }

    /**
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            //为0时说明prefix即为公共前缀（即str[i]的前几个字符匹配了prefix），跳出本次循环，继续遍历
            while (strs[i].indexOf(prefix) != 0){
                //如果没有匹配到，将prefix长度减1，继续匹配
                prefix = prefix.substring(0, prefix.length() - 1);
                //当prefix减为空时还没有匹配到，说明不存在公共前缀
                if (prefix.isEmpty()){
                    return "";
                }
            }
        }
        return prefix;
    }

    /**
     *想象数组的末尾有一个非常短的字符串，使用上述方法依旧会进行 S​S​ 次比较。
     * 优化这类情况的一种方法就是水平扫描。我们从前往后枚举字符串的每一列
     * ，先比较每个字符串相同列上的字符（即不同字符串相同下标的字符）然后再进行对下一列的比较。
     * @param strs
     * @return
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        //遍历第一个字符串
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            //依次和后面每个字符串的相同位置比较
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c){
                    //有两种结束的情况，一种是有一个字符串遍历完了
                    //第二种是后面的某个位置字符串不等于第一个相同位置的字符串
                    return strs[i].substring(0, i);
                }
            }
        }
        //循环走完的情况是第一个字符串即为公共前缀
        return strs[0];
    }

    /**
     * 分治思想
     * @param strs
     * @return
     */
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix4(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix4(String[] strs, int l, int r) {
        if (l == r){
            return strs[l];
        }
        int mid = (l + r) / 2;
        //取得左边的最长公共前缀
        String left = longestCommonPrefix4(strs, l, mid);
        //取得右边的最长公共前缀
        String right = longestCommonPrefix4(strs, mid + 1, r);
        //归并一下，返回左右最长公共前缀
        return common(left, right);
    }

    private String common(String left, String right) {
        int len = Math.min(left.length(), right.length());
        for (int i = 0; i < len; i++) {
            if (left.charAt(i) != right.charAt(i)){
                return left.substring(0, i);
            }
        }
        return left.substring(0, len);
    }


    /**
     * 二分法
     * 思路就是先获取所有字符串中的最小长度，这个长度也就是最大公共前缀的长度
     * 然后选择第一个字符串，先判断该字符串从（0-mid）的字符串是否是公共前缀
     * 如果是，说明应该继续判断mid-high之间的，因此新的low = mid + 1
     * 如果不是，说明mid-high的位置不可能存在可能的公共前缀，舍去。因此新的high = mid - 1
     * 这就是二分法的思想，很巧妙
     * @param strs
     * @return
     */
    public String longestCommonPrefix5(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        //获取所有字符串的最小长度
        int minLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }
        int low = 0;
        int high = minLen;
        while (low <= high){
            int mid = (low + high) / 2;
            if (isCommonPrefix(strs, mid))
                low = mid + 1;
            else
                high = mid - 1;
        }
        return strs[0].substring(0 , (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0 ,len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)){
                return false;
            }
        }
        return true;
    }

    /**
     * 利用字典树这种数据结构, 回头再做
     * @param strs
     * @return
     */
    public String longestCommonPrefix6(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return null;
    }


    public static void main(String[] args) {
        String[] strs = new String[]{"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs));
    }
}

