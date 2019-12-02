package main.java.algorithm.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-23 14:05
 * @Description:
 */
public class Leetcode14 {
    /**
     * 水平扫描法
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String ele = strs[i];
            //flowe  flowe flow
            while (ele.indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()){
                    return "";
                }
            }
        }
        return prefix;
    }


    /**
     * 判断相同位置的字符
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        //i指对应的位置, 遍历第一个字符串
        for (int i = 0; i < strs[0].length(); i++) {
            char ele = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || ele != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        //第一个字符串就是最长公共前缀
        return strs[0];
    }

    /**
     * 分治思想
     * @param strs
     * @return
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int low, int high) {
        if (low == high){
            return strs[low];
        }
        int mid = (low + high) / 2;
        String left = longestCommonPrefix(strs, low, mid);
        String right = longestCommonPrefix(strs, mid + 1, high);
        return common(left, right);
    }

    private String common(String left, String right) {
        int minLen = Math.min(left.length(), right.length());
        for (int i = 0; i < minLen; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, minLen);
    }

    /**
     * 二分法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }
        int low = 0, high = minLen;
        while (low <= high){
            int mid = (low + high) / 2;
            if (isCommon(strs, mid)) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommon(String[] strs, int mid) {
        String str = strs[0].substring(0, mid);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str)) {
                return false;
            }
        }
        return true;
    }


}
