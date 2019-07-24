package java8;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-24 15:52
 * @Description: Collectors测试类
 */
public class CollectorsTest {
    public static void main(String[] args) {
        Student student1 = new Student("mhn", 24);
        Student student2 = new Student("qwe", 26);
        Student student3 = new Student("asd", 76);
        Student student4 = new Student("asd", 76);
        Student student5 = new Student("dec", 76);
        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5);
        //求出三个学生中年龄最小的一个
        students.stream().collect(Collectors.minBy(Comparator.comparingInt(Student::getAge))).ifPresent(System.out::println);
        //求平均值
        Double avg = students.stream().collect(Collectors.averagingInt(Student::getAge));
        System.out.println(avg);
        //求各种汇总信息
        IntSummaryStatistics sum = students.stream().collect(Collectors.summarizingInt(Student::getAge));
        System.out.println(sum);
        //拼接名字
        String nameCollect = students.stream().map(Student::getName).collect(Collectors.joining(",", "<begin> ", " <end>"));
        System.out.println(nameCollect);
        //分组  Map<Integer, List<Student>> -> Map<Integer, Map<String, List<Student>>>
        Map<Integer, Map<String, List<Student>>> collect = students.stream().
                collect(Collectors.groupingBy(Student::getAge, Collectors.groupingBy(Student::getName)));
        System.out.println(collect);
        //分区
        Map<Boolean, List<Student>> collect1 = students.stream().collect(Collectors.partitioningBy(x -> x.getAge() > 70));
        System.out.println(collect1);

    }
}
