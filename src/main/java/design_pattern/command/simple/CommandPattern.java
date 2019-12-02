package main.java.design_pattern.command.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-16 14:34
 * @Description:  命令模式
 */

//抽象命令
interface Command {

    void execute();
}

//具体命令
class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand() {
        this.receiver = new Receiver();
    }

    @Override
    public void execute() {
        receiver.action();
    }
}


//调用者
class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void call() {
        System.out.println("调用者执行命令Command");
        command.execute();
    }
}

//接收者
class Receiver {
    public void action() {
        System.out.println("接收者的action()方法被调用");
    }
}


public class CommandPattern {
    public static void main(String[] args) {
        Command command = new ConcreteCommand();
        Invoker invoker = new Invoker(command);
        System.out.println("客户访问调用者的call()方法");
        invoker.call();
    }

}
