package algorithm.stack_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-22 16:00
 * @Description:
 */
public class ValidParentheses {
    private Map<Character, Character> map = new HashMap<>();

    public ValidParentheses(){
        this.map.put('}', '{');
        this.map.put(']', '[');
        this.map.put(')', '(');

    }

    /**
     * 利用栈
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char element = s.charAt(i);
            if (map.containsKey(element)) {
                char left = stack.isEmpty() ? '#' : stack.pop();
                if (map.get(element) != left) {
                    return false;
                }
            }else {
                stack.push(element);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 巧妙的思想，类似于消消乐，只是时间复杂度比较高
     * @param s
     * @return
     */
    public boolean isValid2(String s){
        while (s.contains("[]") || s.contains("()") || s.contains("{}")){
            s = s.replace("{}","");
            s = s.replace("()", "");
            s = s.replace("[]", "");
        }
        if (!"".equals(s)){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "()";
        ValidParentheses vp = new ValidParentheses();
        boolean flag = vp.isValid(str);
        System.out.println(flag);
    }
}
