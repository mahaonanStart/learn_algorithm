package design_pattern.command.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: M˚Haonan
 * @Date: 2019-12-03 17:36
 * @Description: 命令模式练习测试
 */

interface Command {
    void execute();

    void undo();
}

class LightReciver {
    public void on() {
        System.out.println("灯打开");
    }

    public void off() {
        System.out.println("灯关闭");
    }
}

abstract class OnCommand implements Command {

}

abstract class OffCommand implements Command {

}

class LightOnCommand extends OnCommand {

    private LightReciver reciver;

    public LightOnCommand(LightReciver reciver) {
        this.reciver = reciver;
    }

    @Override
    public void execute() {
        reciver.on();
    }

    @Override
    public void undo() {
        reciver.off();
    }
}

class LightOffCommand extends OffCommand {

    private LightReciver reciver;

    public LightOffCommand(LightReciver reciver) {
        this.reciver = reciver;
    }

    @Override
    public void execute() {
        reciver.off();
    }

    @Override
    public void undo() {
        reciver.on();
    }
}

class NoCommand implements Command {

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}

class RemoteController {

    private Command undoCommand;
    private Map<Integer, Command> onCommands;
    private Map<Integer, Command> offCommands;

    public RemoteController() {
        onCommands = new HashMap<>();
        offCommands = new HashMap<>();
        undoCommand = new NoCommand();
    }

    public void setCommand(Integer type, Command command) {
        if (command instanceof OnCommand) {
            onCommands.put(type, command);
        }else if (command instanceof OffCommand) {
            offCommands.put(type, command);
        }
    }

    public void onButton(Integer type) {
        Command command = onCommands.get(type);
        if (command == null) {
            command = new NoCommand();
        }
        command.execute();
        setUndoCommand(command);
    }

    private void setUndoCommand(Command command) {
        this.undoCommand = command;
    }

    public void offButton(Integer type) {
        Command command = offCommands.get(type);
        if (command == null) {
            command = new NoCommand();
        }
        command.execute();
        setUndoCommand(command);
    }

    public void undoButton() {
        undoCommand.undo();
    }


}

public class CommandTest {
    public static void main(String[] args) {
        LightReciver reciver = new LightReciver();
        Command onCommand = new LightOnCommand(reciver);
        Command offCommand = new LightOffCommand(reciver);
        RemoteController controller = new RemoteController();
        controller.setCommand(1, onCommand);
        controller.setCommand(1, offCommand);
        controller.onButton(1);
        controller.onButton(1);
        controller.undoButton();
    }
}
