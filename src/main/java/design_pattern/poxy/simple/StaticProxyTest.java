package design_pattern.poxy.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-29 16:44
 * @Description: 静态代理测试
 */

//抽象主题类
interface Subject {
    void request();
}

//目标对象
class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("真实主题方法被调用...");
    }
}

//静态代理类
class StaticProxy implements Subject {

    private Subject realSubject;

    public void preRequest() {
        System.out.println("访问真实主题之前的预处理");
    }

    public void postRequest() {
        System.out.println("访问真是主体之后的后续处理");
    }

    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.request();
        postRequest();
    }
}


public class StaticProxyTest {

    public static void main(String[] args) {
        StaticProxy proxy = new StaticProxy();
        proxy.request();
    }
}
