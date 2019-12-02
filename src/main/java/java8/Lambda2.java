package main.java.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-08 22:40
 * @Description: lambda和函数式接口测试2
 */
class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

public class Lambda2 {

    /**
     * 使用BitFunction函数式接口实现功能
     * @param age
     * @param persons
     * @return
     */
    public List<Person> getPersonByAge(int age, List<Person> persons) {
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (a, personList) -> {
            return personList.stream().filter(person -> person.getAge() > a).collect(Collectors.toList());
        };
        return biFunction.apply(age, persons);
    }

    /**
     * 直接使用stream流的方式
     * @param name
     * @param persons
     * @return
     */
    public List<Person> getPersonByName(String name, List<Person> persons) {
        return persons.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
    }

    /**
     * 更加灵活的定义方式，用户可以在调用时自己传入行为
     * @param age
     * @param persons
     * @param biFunction
     * @return
     */
    public List<Person> getPersonByAge2(int age, List<Person> persons, BiFunction<Integer, List<Person>, List<Person>> biFunction){
        return biFunction.apply(age, persons);
    }

    public static void main(String[] args) {
        //测试内容
        Person p1 = new Person("mhn", 23);
        Person p2 = new Person("nhh", 24);
        List<Person> personList = Arrays.asList(p1, p2);
        Lambda2 lambda2 = new Lambda2();
        //main.java.algorithm.test one
        List<Person> personByAge = lambda2.getPersonByAge(23, personList);
        personByAge.forEach(person -> System.out.println(person.getAge()));
        //main.java.algorithm.test two
        List<Person> personByName = lambda2.getPersonByName("mhn", personList);
        personByName.forEach(person -> System.out.println(person.getName()));
        //main.java.algorithm.test three
        List<Person> personByAge2 = lambda2.getPersonByAge2(23, personList, (age, persons) -> {
            return personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList());
        });
        personByAge2.forEach(person -> System.out.println(person.getAge()));
        //直接写，不调用方法
        personList.stream().filter(person -> person.getName().equals("nhh")).collect(Collectors.toList()).forEach(person -> System.out.println(person.getName()));
    }
}
