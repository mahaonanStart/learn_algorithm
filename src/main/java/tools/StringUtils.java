package main.java.tools;

/**
 * @Author: M˚Haonan
 * @Date: 2019-09-09 11:40
 * @Description:
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 首字母小写
     * @return
     */
    public static String firstLetterToLowCase(String str) {
        if (isBlack(str)) {
            return "";
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 判断字符串为null或者""
     * @param str
     * @return
     */
    public static boolean isBlack(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 驼峰命名转下划线命名
     * @param str
     * @return
     */
    public static String camelToUnderline(String str) {
        if (isBlack(str)) {
            return "";
        }
        if (str.length() == 1) return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            char ele = str.charAt(i);
            if (65 <= ele && ele <= 90) {
                sb.append("_").append((char)(ele + 32));
            }else {
                sb.append(ele);
            }
        }
        return sb.toString();
    }




    public static void main(String[] args) {
        String myFistLetter = camelToUnderline("myFirstLetter");
        System.out.println(myFistLetter);
    }
}
