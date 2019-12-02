package main.java.design_pattern.adapter.springmvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-05 23:25
 * @Description:
 */
public class DispatchServlet {

    public static List<HandlerAdapter> adapters = new ArrayList<>();
    public DispatchServlet() {
        adapters.add(new SimpleHandlerAdapter());
        adapters.add(new AnnotationHandlerAdapter());
    }


    public void doDispatch() {
        Object controller = new SimpleController();
        HandlerAdapter adapter = getHandler(controller);
        //这里只需要统一调用适配器的handler方法即可,以后扩展起来也不用修改，只需要扩展对应的适配器类
        //这里假如不用适配器模式，就需要去判断是什么类型的控制器，然后去调用对应的方法,会写很多if else
        //并且扩展新的controller时也会修改代码，不符合开闭原则
        adapter.handle(controller);
    }

    /**
     * 根据不同的controller获取对应的adapter
     * @param controller
     * @return
     */
    private HandlerAdapter getHandler(Object handle) {
        for (HandlerAdapter adapter : adapters) {
            if (adapter.support(handle)) {
                return adapter;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DispatchServlet dispatchServlet = new DispatchServlet();
        dispatchServlet.doDispatch();
    }

}
