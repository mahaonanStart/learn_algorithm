package design_pattern.adapter.springmvc;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-05 23:16
 * @Description: 控制器接口
 */
public interface Controller {

}

class SimpleController implements Controller {

    public void doSimple() {
        System.out.println("simple...");
    }
}

class AnnotationController implements Controller {

    public void doAnnotation() {
        System.out.println("annotation...");
    }
}