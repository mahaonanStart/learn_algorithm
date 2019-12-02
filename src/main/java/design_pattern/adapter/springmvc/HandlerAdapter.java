package design_pattern.adapter.springmvc;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-05 23:18
 * @Description: 适配器接口及其实现类，对应不同的controller
 */
//适配器类统一的接口，也是目标接口,handle()即我们希望的目标方法,统一的规范
public interface HandlerAdapter {

    boolean support(Object handle);

    void handle(Object handle);
}

//实现目标接口的同时又引用了源接口(这里强转成了具体的类)的方法
class SimpleHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handle) {
        return (handle instanceof SimpleController);
    }

    @Override
    public void handle(Object handle) {
        ((SimpleController) handle).doSimple();
    }
}

class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handle) {
        return (handle instanceof AnnotationController);
    }

    @Override
    public void handle(Object handle) {
        ((AnnotationController) handle).doAnnotation();
    }
}
