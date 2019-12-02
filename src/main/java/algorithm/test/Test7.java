package main.java.algorithm.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-28 23:28
 * @Description:
 */
public class Test7 implements Serializable {
    private static Integer a = 10;
    private transient int b = 10;
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int a = 1;
        String b = "abc";
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Test7 test7 = new Test7();
        test7.test(a, b, list);
        System.out.println(a);
        System.out.println(b);
        System.out.println(list);
    }

    public void test(int a, String b, List<String> list){
        a = 4;
        b = "cd";
        list.add("4");
    }

}
