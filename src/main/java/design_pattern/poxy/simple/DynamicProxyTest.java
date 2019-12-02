package main.java.design_pattern.poxy.simple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-29 17:07
 * @Description: 动态代理测试
 */

interface Service {
    void request();
}

class ServiceImpl implements Service {

    @Override
    public void request() {
        System.out.println("真实业务类被调用");
    }
}

class ServiceProxy implements InvocationHandler {

    private Object target;

    public Object bind(Object obj) {
        this.target = obj;
        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), target.getClass().getInterfaces(), this);
        return proxy;
    }

    public void prepare() {
        System.out.println("调用业务之前的预处理");
    }

    public void after() {
        System.out.println("调用业务之后的后续处理");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        prepare();
        Object invoke = method.invoke(this.target, args);
        after();
        return invoke;
    }
}

public class DynamicProxyTest {

    public static void main(String[] args) {
        Service service = (Service) new ServiceProxy().bind(new ServiceImpl());
        service.request();
    }
}
