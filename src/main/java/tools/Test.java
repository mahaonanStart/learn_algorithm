package main.java.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-09 11:07
 * @Description:
 */

interface Person {
    void startUp();
}

class Father implements Person{

    public void startUp() {
        System.out.println("startUp执行了");
    }
}
class Son extends Father{

}

public class Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?>[] classes = new Class[3];
        classes[0] = Person.class;
        classes[1] = Father.class;
        classes[2] = Son.class;
        List<Person> list = new ArrayList<>();
        for (Class<?> aClass : classes) {
            if (!aClass.isInterface()) {
                list.add((Person) aClass.newInstance());
            }
        }
        for (Person person : list) {
            person.startUp();
        }
    }
}
