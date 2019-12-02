package design_pattern.state.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-22 15:56
 * @Description: 状态模式测试
 */
//环境类
class Context {
    private State state;
    //定义环境类的初始状态
    public Context() {
        this.state = new ConcreteStateA();
    }

    //读取状态
    public State getState() {
        return state;
    }

    //设置状态
    public void setState(State state) {
        this.state = state;
    }

    //对请求做处理
    public void handle() {
        state.handle(this);
    }
}

//抽象状态类
abstract class State {
    public abstract void handle(Context context);
}

//具体状态A类
class ConcreteStateA extends State {

    @Override
    public void handle(Context context) {
        System.out.println("当前状态是A");
        context.setState(new ConcreteStateA());
    }

}

//具体状态B类
class ConcreteStateB extends State {

    @Override
    public void handle(Context context) {
        System.out.println("当前状态是B");
        context.setState(new ConcreteStateB());
    }

}

public class StatePatternClient {
    public static void main(String[] args) {
        Context context = new Context();
        context.handle();
        context.setState(new ConcreteStateB());
        context.handle();
        context.handle();
    }
}
