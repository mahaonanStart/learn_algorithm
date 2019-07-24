package java8;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-07 20:33
 * @Description: lambda表达式和函数式接口
 */
public class Lambda {

    public int compute(int a, Function<Integer, Integer> function){
        return function.apply(a);
    }

    public int compute(int a, Function<Integer, Integer> fun1, Function<Integer, Integer> fun2) {
        return fun1.compose(fun2).apply(a);
    }

    public int compute2(int a, Function<Integer, Integer> fun1, Function<Integer, Integer> fun2) {
        return fun1.andThen(fun2).apply(a);
    }

    public static void main(String[] args) {
        //lambda表达式的第一个参数一定是调用toUpperCase的字符串对象
        //String为返回值
        Function<String, String> func = String::toUpperCase;
        Function<String, String> fun2 = str -> {
            return str.toUpperCase();
        };
        Function<String, String> fun3 = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };
        System.out.println(func.getClass().getInterfaces()[0]);
        Consumer<String> con = System.out::println;
        
        //---------------------------------------

        Lambda lambda = new Lambda();
        // 使用匿名内部类
        // 实际上就是传递了一个Function接口的实现对象，重写了apply方法
        //因此如果用lambda表达式，看起来是传递了一种行为，即apply里面重写的行为
        lambda.compute(1, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        });
        int compute = lambda.compute(1, value -> value * 2);
        System.out.println(compute);
        System.out.println(lambda.compute(2, value -> value * 3, value -> value * value));
        System.out.println(lambda.compute2(2, value -> value * 3, value -> value * value));


    }
}
