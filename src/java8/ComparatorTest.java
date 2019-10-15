package java8;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-25 17:26
 * @Description: 比较器测试
 */
public class ComparatorTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("woaini", "nihao", "hello", "world");
//        Collections.sort(list);
//        Collections.sort(list, (a, b) -> a.length() - b.length());
//        Collections.sort(list, Comparator.comparingInt(String::length).reversed());
        //必须显示指定，推断不了
//        Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).reversed());
        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        System.out.println(list);
    }
}
