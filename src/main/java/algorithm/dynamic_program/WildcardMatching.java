package main.java.algorithm.dynamic_program;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-08-12 17:01
 * @Description:
 *
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 *
 * leetcode 44
 */
public class WildcardMatching {

    /**
     * 回溯递归，正确，但是会超时
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match =  p.charAt(0) == '*' || (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?'));
        //如果p的第一个字符为*
        if (p.charAt(0) == '*') {
            //如果后面什么都没有，则s和p一定匹配
            if (p.length() == 1) return true;
            //如果*之后紧挨的字符为*， 或者s为''
            if (s.length() == 0 || p.charAt(1) == '*') {
                return isMatch(s, p.substring(1));
            }
            //如果*之后紧挨的字符为?
            if (p.charAt(1) == '?') {
                return isMatch(s.substring(1), '*' + p.substring(2));
            }
            //如果*之后紧挨的字符为s中出现的某一个字符
            if (s.contains(String.valueOf(p.charAt(1)))) {
                List<Integer> jumps = getJump(s, p.charAt(1));
                boolean flag = false;
                for (Integer jump : jumps) {
                    if (isMatch(s.substring(jump), p.substring(2))) {
                        flag = true;
                    };
                }
                return flag;
            //如果*之后紧挨的字符不在s中
            }else {
                return false;
            }
        //如果p的第一个字符不是*
        }else {
            return first_match && isMatch(s.isEmpty() ? s: s.substring(1), p.substring(1));
        }
    }

    public List<Integer> getJump(String s, char ele) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (ele == s.charAt(i)) {
                list.add(i + 1);
            }
        }
        return list;
    }

    /**
     * 动态规划
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()]=true;
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                match[s.length()][i]=true;
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')
                    match[i][j]=match[i+1][j+1];
                else if(p.charAt(j)=='*')
                    match[i][j]=match[i+1][j]||match[i][j+1];
                else
                    match[i][j]=false;
            }
        }
        return match[0][0];
    }

    public boolean isMatch3(String s, String p) {
        //dp状态定义，代表s的i个字符与p的j个字符匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //s，p都为空时，肯定为true
        dp[0][0] = true;
        //当s为空时，只有p为*才匹配
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) != '*') {
                break;
            }else {
                dp[0][i] = true;
            }
        }
        //开始dp递推
        //s从下标0也就是第一个字符开始
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }else if (p.charAt(j - 1) == '*') {
                    //dp[i][j - 1]代表*匹配了空字符串
                    //dp[i - 1][j]代表*匹配了非空字符串，由于*可能匹配多个字符串，因此只是把i往前挪一位，j不动
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    public static void main(String[] args) {
        WildcardMatching wildcardMatching = new WildcardMatching();
        System.out.println(wildcardMatching.isMatch2("adceb", "*a*b"));
    }

}
