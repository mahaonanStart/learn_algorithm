package main.java.algorithm.test;

import java.util.Stack;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-12 17:57
 * @Description:
 */
public class Test9 {

    public static String getReverStr(String str){
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ele = str.charAt(i);
            if (97 <= ele && ele <= 122){
                stack.push(ele);
            }else{
                while (!stack.empty()){
                    sb.append(stack.pop());
                }
                sb.append(ele);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String result = getReverStr("I love! you.");
        System.out.println(result);
    }
}
