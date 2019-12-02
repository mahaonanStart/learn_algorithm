package main.java.design_pattern.principle.dependency_inversion;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-24 11:34
 * @Description: 依赖倒转原则
 */
public class DependencyInversion {
    public static void main(String[] args) {
        Person person = new Person();
        person.reciver(new Email());
        person.reciver(new IChat());
    }
}

interface IReciver {
    String post();
}

class Email implements IReciver {

    @Override
    public String post() {
        return "电子邮件发送消息";
    }
}

class IChat implements IReciver {

    @Override
    public String post() {
        return "微信发送消息";
    }
}

class Person {
    public void reciver(IReciver reciver) {
        System.out.println(reciver.post());
    }
}
