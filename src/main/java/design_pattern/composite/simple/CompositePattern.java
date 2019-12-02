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
class Leaf implements Component {

    private String name;

    public Leaf(String name) {
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

    public static void main(String[] args)
    {
        Component c0=new Composite();
        Component c1=new Composite();
        Component leaf1=new Leaf("1");
        Component leaf2=new Leaf("2");
        Component leaf3=new Leaf("3");
        c0.add(leaf1);
        c0.add(c1);
        c1.add(leaf2);
        c1.add(leaf3);
        c0.operation();
    }

}
