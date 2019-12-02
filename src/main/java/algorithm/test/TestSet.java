package algorithm.test;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-24 16:14
 * @Description: 测试set集合
 */
public class TestSet {
    public static void main(String[] args) {
        Set<List<Person>> set = new HashSet<>();
        List<Person> l1 = new ArrayList<>();
        l1.add(new Person("mhn", 23));
        List<Person> l2 = new ArrayList<>();
        l2.add(new Person("mhn", 23));
        System.out.println(set.add(l1));
        System.out.println(set.add(l2));
    }
}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
