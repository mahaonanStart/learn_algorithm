package main.java.java8;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-15 10:53
 * @Description: 默认方法测试
 */
interface MyInterface1{
    default void myMethod(){
        System.out.println("MyInterface1");
    }
}

class impl implements MyInterface1{
    public void myMethod(){
        System.out.println("impl1");
    }
}

interface MyInterface2{
    default void myMethod(){
        System.out.println("MyInterface2");
    }
}

public class DefaultMethodTest extends impl implements MyInterface2{


    public static void main(String[] args) {
        DefaultMethodTest test = new DefaultMethodTest();
        test.myMethod();
    }
}
