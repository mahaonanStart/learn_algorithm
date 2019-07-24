package java8;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-19 12:07
 * @Description: 流的回顾测试
 */
public class StreamTest2 {
    public static void main(String[] args) {
        Stream stream1 = Stream.of("hello", "world", "hello world");
        String s = stream1.collect(Collectors.joining(",")).toString();
//        System.out.println(s);

        List<String> list = Arrays.asList("hello", "world", "helloworld", "test");
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
//        list1.stream().map(item -> list2.stream().map(item2 -> item + item2)).flatMap(item -> item).forEach(System.out::println);
//        list1.stream().flatMap(item -> list2.stream().map(item2 -> item + item2)).forEach(System.out::println);


        List<String> list3 = Arrays.asList("hello welcome", "hello world", "hello world hello", "hello welcome");
        Map<String, Integer> result = new HashMap<>();
        //Collector<T, ?, Map<K, List<T>>
        list3.stream().flatMap(item -> Arrays.stream(item.split(" "))).collect(Collectors.groupingBy(i -> i)).
                forEach((k ,v) -> result.put(k, v.size()));

        Map<String, Long> collect = list3.stream().flatMap(item -> Arrays.stream(item.split(" "))).collect(Collectors.
                groupingBy(i -> i, Collectors.counting()));
        System.out.println(collect);
        //System.out.println(result);
    }
}
