import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: M˚Haonan
 * @Date: 2019-12-02 15:37
 * @Description:
 */
public class WorkHandler implements InvocationHandler {

    private Object object;

    public WorkHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(123);
        System.out.println(proxy.getClass().getName());
        return method.invoke(object, args);
    }

    public static void main(String[] args) {
        String str = new String("mhn");
        WorkHandler workHandler = new WorkHandler(str);
        Serializable o = (Serializable) Proxy.newProxyInstance(str.getClass().getClassLoader(), str.getClass().getInterfaces(), workHandler);
//        System.out.println(o.getClass().getSimpleName());
        System.out.println(o.toString());
    }
}
