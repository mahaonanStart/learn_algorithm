package main.java.java8;

import java.util.function.Supplier;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-13 21:21
 * @Description: Supplier函数式接口测试
 */
class Student{
	private String name = "mhn";
	private int age = 23;
	
	public Student(){
		
	}

    public Student(String name, int age) {
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


public class SupplierTest {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> "mhn";
		System.out.println(supplier.get());
		Supplier<Student> spl = () -> new Student();
		System.out.println(spl.get().getName());
		Supplier<Student> spl2 = Student::new;
		System.out.println(spl2.get().getName());
    }
}
