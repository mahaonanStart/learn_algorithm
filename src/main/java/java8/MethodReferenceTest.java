package main.java.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-14 18:05
 * @Description: 方法引用测试
 */
class Student2{
    private int score;
    private String name;

    public Student2(int score, String name){
        this.score = score;
        this.name = name;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public static int comparatorByScore(Student2 stu1, Student2 stu2){
        return stu1.getScore() - stu2.getScore();
    }
    public static int comparatorByName(Student2 stu1, Student2 stu2){
        return stu1.getName().compareToIgnoreCase(stu2.getName());
    }

    public int comparatorByScore2(Student2 student){
        return this.getScore() - student.getScore();
    }
    
}

class StudentComparator{
    public int comparatorByScore(Student2 stu1, Student2 stu2){
        return stu1.getScore() - stu2.getScore();
    }
}


public class MethodReferenceTest {

    public String getString(Supplier<String> supplier){
        return supplier.get() + "hello";
    }

    public String getString2(String str, Function<String, String> function){
        return function.apply(str);
    }

    

    public static void main(String[] args) {
        // List<String> list = Arrays.asList("mhn", "nhh");
        // list.forEach(System.out:: println);
        Student2 student1 = new Student2(60, "mhn");
        Student2 student2 = new Student2(10, "nhh");
        Student2 student3 = new Student2(90, "zzf");
        List<Student2> list = Arrays.asList(student1, student2, student3);
        // list.sort((stu1, stu2) -> Student2.comparatorByScore(stu1, stu2));
        // list.forEach(stu -> System.out.println(stu.getScore()));
        //类名:: 静态方法名的形式
        // list.sort(Student2::comparatorByScore);
        // list.forEach(stu -> System.out.println(stu.getName()));
        //对象::实例方法名的形式
        StudentComparator sc = new StudentComparator();
        list.sort(sc:: comparatorByScore);
        //类名:: 实例方法名的形式
        //lambda表达式的第一个参数调用了comparatorByScore2这个方法
        list.sort(Student2:: comparatorByScore2);
        list.sort((o1, o2) -> o1.getScore() - o2.getScore());

        //构造方法名::new
        MethodReferenceTest test = new MethodReferenceTest();
        System.out.println(test.getString(String:: new));

        System.out.println(test.getString2("world", String::new));
        
    }   

}
