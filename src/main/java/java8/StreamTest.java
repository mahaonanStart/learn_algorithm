package java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-15 15:01
 * @Description: 流测试
 */
class Teacher{
    private String name;
    private int age;

    public Teacher() {
    }

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(name, teacher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class StreamTest {

    public static void main(String[] args) {
        //通过静态方法of创建Stream
        Stream stream1 = Stream.of("hello", "world", "hello world");
        String[] arr = new String[]{"hello", "world", "hello world"};
        Stream stream2 = Stream.of(arr);
        //通过Arrays的stream方法
        Stream stream3 = Arrays.stream(arr);
        //通过集合创建
        List<String> list = Arrays.asList(arr);
        Stream stream4 = list.stream();
        //流简单的使用
        IntStream.of(1, 3, 5, 7, 9).forEach(System.out:: println);
        IntStream.range(3, 8).forEach(System.out:: println);
        IntStream.rangeClosed(3, 7).forEach(System.out:: println);
        //对一个list的元素乘2再求和
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = list2.stream().map(item -> item * 2).reduce(0, Integer:: sum);
        System.out.println(sum);
        //stream转换为数组
        Stream<String> stream5 = Stream.of("hello", "world", "helloWorld");
//        String[] strings = stream5.toArray(length -> new String[length]);
//        Arrays.asList(strings).forEach(System.out:: println);
//        String[] strings2 = stream5.toArray(String[]::new);
        //stream转换为list
//        List<String> list3 = stream5.collect(Collectors.toList());
        //原始方式转list
        //第一个为返回的结果，是一个list
        //第二个参数为BiConsumer，接受两个参数，第一个参数为list，第二个参数为list中元素的类型，无返回值。相当于每次往list中添加元素
        //第三个参数为BiConsumer，两个参数均为list，把第二步添加的list中的元素汇总
//        List<String> list4 = stream5.collect(() -> new ArrayList<>(), (thisList, ele) -> thisList.add(ele),
//        (thisList1, thisList2) -> thisList1.addAll(thisList2));
//        //使用方法引用
//        List<String> lsit5 = stream5.collect(ArrayList:: new, ArrayList:: add, ArrayList:: addAll);
//        //stream转化为任意集合
//        List<String> list6 = stream5.collect(Collectors.toCollection(ArrayList::new));
//        Set<String> set = stream5.collect(Collectors.toCollection(TreeSet::new));
//        将流中的字符串拼接
        //   String str = stream5.collect(StringBuilder:: new, StringBuilder:: append, StringBuilder:: append).toString();
        //   System.out.println(str);
        //  String str2 = stream5.collect(Collectors.joining());
        //  System.out.println(str2);
        //转换为大写然后输出
        List<String> sList = Arrays.asList("hello", "world", "helloWorld");
        sList.stream().map(String:: toUpperCase).forEach(System.out:: println);

        //flatmap
        Stream<List<Integer>> stream6 = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        stream6.flatMap(Collection::stream).map(i -> i * i).forEach(System.out:: println);
        //generate
        Stream<String> stream7 = Stream.generate(UUID.randomUUID():: toString);
        stream7.findFirst().ifPresent(System.out:: println);
        //iterate
        Stream.iterate(1, i -> i + 2).limit(6).forEach(System.out:: println);

        int sum1 = Stream.iterate(1, i -> i + 2).limit(6).map(i -> i * 2).skip(2).limit(2).reduce(0, Integer:: sum);
//        System.out.println(sum1);
        //
        IntSummaryStatistics summaryStatistics = Stream.iterate(1, i -> i + 2).limit(6).mapToInt(i -> i * 2).skip(2).limit(2).summaryStatistics();
        int max = summaryStatistics.getMax();
//        System.out.println(max);
        //去重
        Stream<Teacher> stream = Stream.of(new Teacher("mhn", 23), new Teacher("mhn", 24));
        stream.distinct().forEach(item -> System.out.println(item.getName()));

        //找出长度为5的第一个单词并输出
        List<String> list1 = Arrays.asList("hello", "world", "hello world");
//        list1.stream().filter(item -> item.length() == 5).findFirst().ifPresent(item -> System.out.println(item + "的长度为" + 5));
        //流的短路
        list1.stream().mapToInt(String::length).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
        list1.stream().mapToInt(item -> {
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
        //输出所有单词并去重
        List<String> list3 = Arrays.asList("hello welcome", "hello world", "hello world hello", "hello welcome");
        list3.stream().flatMap(str -> Arrays.stream(str.split(" "))).distinct().forEach(System.out:: println);
        //flatmap
        List<String> list4 = Arrays.asList("hi", "hello", "你好");
        List<String> list5 = Arrays.asList("mhn", "nhh");
        list4.stream().flatMap(item -> list5.stream().map(item2 -> item2 + " " + item)).forEach(System.out:: println);
        list4.stream().flatMap(item -> list5.stream().map(item2 -> item2 + " " + item)).forEach(System.out:: println);
        //分组统计单词数量(wordcount) 一
        Map<String, Integer> result = new HashMap<>();
        list3.stream().flatMap(str -> Arrays.stream(str.split(" "))).collect(Collectors.groupingBy(s -> s)).
                forEach((k, v) -> result.put(k, v.size()));
        System.out.println(result);
        //分组统计单词数量(wordcount) 二
        Map<String, Long> collect = list3.stream().flatMap(str -> Arrays.stream(str.split(" "))).
                collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println(collect);
        //分区
        Map<Boolean, List<String>> collect1 = list4.stream().collect(Collectors.partitioningBy(str -> str.length() > 2));
        System.out.println(collect1);

    }
}
