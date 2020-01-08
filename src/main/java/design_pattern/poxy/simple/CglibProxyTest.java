package design_pattern.poxy.simple;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: M˚Haonan
 * @Date: 2019-12-02 17:21
 * @Description: cglib动态代理
 */
class Book {
    public void pay() {
        System.out.println("今天买了一本书");
    }
}

class BookProxy implements MethodInterceptor {

    private Object obj;

    public BookProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("花了50元");
        return method.invoke(this.obj, args);
    }
}

public class CglibProxyTest {
    public static void main(String[] args) {
        Book book = new Book();         //真实对象
        Enhancer enhancer = new Enhancer();     //代理工具类对象
        enhancer.setSuperclass(Book.class);     //设置一个假定的父类
        enhancer.setCallback(new BookProxy(book));
        Book proxy = (Book) enhancer.create();      //创建代理对象
        proxy.pay();
    }
}
