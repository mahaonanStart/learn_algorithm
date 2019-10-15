package design_pattern.composite.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-15 17:58
 * @Description: 组合模式
 */
//抽象构件
interface Component {

    void add(Component c);
    void remove(Component c);
    Component getChild(int i);
    void operation();
}

//树叶构件
class leaf implements Component {

    private String name;

    public leaf(String name) {
        this.name = name;
    }

    @Override
    public void add(Component c) {

    }

    @Override
    public void remove(Component c) {

    }

    @Override
    public Component getChild(int i) {
        return null;
    }

    @Override
    public void operation() {
        System.out.println("树叶" + name + "被访问");
    }
}

//树枝构件
class Composite implements Component {

    private List<Component> children = new ArrayList<>();

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public Component getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }
}


public class CompositePattern {


}
