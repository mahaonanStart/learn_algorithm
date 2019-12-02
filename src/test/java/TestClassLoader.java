package test.java;

/**
 * @Author: M˚Haonan
 * @Date: 2019-12-02 11:51
 * @Description: 类加载器测试
 */
class Person {

    private String name = "mhn";

    private int age = 24;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class TestClassLoader {


    public void testClassLoader() throws Exception {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?> cls = classLoader.loadClass("test.java.Person");
        Person person = (Person) cls.newInstance();
        System.out.println(person);
    }

    public static void main(String[] args) throws Exception{
        TestClassLoader testClassLoader = new TestClassLoader();
        testClassLoader.testClassLoader();
    }
}
