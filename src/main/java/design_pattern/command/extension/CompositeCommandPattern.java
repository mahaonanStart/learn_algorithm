package main.java.design_pattern.command.extension;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-16 15:02
 * @Description: 组合命令模式
 *
 */

interface AbstractCommand {
    void execute();
}

//树叶构件1,具体命令角色
class ConcreteCommand1 implements AbstractCommand {

    private CompositeReceiver compositeReceiver;

    public ConcreteCommand1() {
        compositeReceiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        compositeReceiver.action1();
    }
}

class ConcreteCommand2 implements AbstractCommand {

    private CompositeReceiver compositeReceiver;

    public ConcreteCommand2() {
        compositeReceiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        compositeReceiver.action2();
    }
}

//树枝构件，调用者
class CompositeInvoker implements AbstractCommand {

    private List<AbstractCommand> commandList = new ArrayList<>();

    public void add(AbstractCommand ac) {
        commandList.add(ac);
    }

    public void remove(AbstractCommand ac) {
        commandList.remove(ac);
    }

    public AbstractCommand getChild(int i) {
        return commandList.get(i);
    }

    @Override
    public void execute() {
        for (AbstractCommand abstractCommand : commandList) {
            abstractCommand.execute();
        }
    }
}

//接收者
class CompositeReceiver {
    public void action1() {
        System.out.println("接收者的action1()被调用");
    }
    public void action2() {
        System.out.println("接收者的action2()被调用");
    }
}

public class CompositeCommandPattern {

    public static void main(String[] args) {
        AbstractCommand command1 = new ConcreteCommand1();
        AbstractCommand command2 = new ConcreteCommand2();
        CompositeInvoker invoker = new CompositeInvoker();
        invoker.add(command1);
        invoker.add(command2);
        invoker.execute();
    }
}
