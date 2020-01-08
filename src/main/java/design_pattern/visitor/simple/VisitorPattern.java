package design_pattern.visitor.simple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-12-06 16:41
 * @Description: 访问者模式
 */

//抽象元素类
interface Element {
    void accept(Visitor visitor);
}

class ConcreteElementA implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }

    public String operationA() {
        return "具体元素A的操作";
    }
}

class ConcreteElementB implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }

    public String operationB() {
        return "具体元素B的操作";
    }
}

//抽象访问者
interface Visitor {

    void visitor(ConcreteElementA elementA);

    void visitor(ConcreteElementB elementB);

}

class ConcreteVisitorA implements Visitor {

    @Override
    public void visitor(ConcreteElementA elementA) {
        System.out.println("具体访问者A访问-->" + elementA.operationA());
    }

    @Override
    public void visitor(ConcreteElementB elementB) {
        System.out.println("具体访问者A访问-->"  + elementB.operationB());
    }
}

class ConcreteVisitorB implements Visitor {

    @Override
    public void visitor(ConcreteElementA elementA) {
        System.out.println("具体访问者B访问-->" + elementA.operationA());
    }

    @Override
    public void visitor(ConcreteElementB elementB) {
        System.out.println("具体访问者B访问-->" + elementB.operationB());
    }
}

class ObjectStructure {
    private List<Element> list = new ArrayList<>();
    public void accept(Visitor visitor) {
        Iterator<Element> i = list.iterator();
        while (i.hasNext()) {
            i.next().accept(visitor);
        }
    }

    public void add(Element element) {
        list.add(element);
    }

    public void remove(Element element) {
        list.remove(element);
    }
}

public class VisitorPattern {

    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor = new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("--------------------");
        visitor = new ConcreteVisitorB();
        os.accept(visitor);
    }
}
