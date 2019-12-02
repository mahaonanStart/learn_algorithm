package design_pattern.chain_of_responsibility.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-16 15:34
 * @Description: 责任链模式
 */
//抽象处理者角色
abstract class Handler {
    private Handler next;

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    //处理请求的方法
    public abstract void handleRequest(String request);
}

class ConcreteHandler1 extends Handler {

    @Override
    public void handleRequest(String request) {
        if ("one".equals(request)) {
            System.out.println("具体处理者1负责处理该请求");
        }else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            }else {
                System.out.println("没人处理该请求");
            }
        }
    }
}

class ConcreteHandler2 extends Handler {

    @Override
    public void handleRequest(String request) {
        if ("two".equals(request)) {
            System.out.println("具体处理者2负责处理该请求");
        }else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            }else {
                System.out.println("没人处理该请求");
            }
        }
    }
}
public class ChainOfResponsibilityPattern {

    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        handler1.handleRequest("two");
    }
}
