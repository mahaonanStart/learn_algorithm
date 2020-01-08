package design_pattern.iterator.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-12-06 17:47
 * @Description: 迭代器模式
 */


//抽象迭代器
interface Iterator {
    Object first();

    Object next();

    boolean hasNext();
}


class ConcreteIterator implements Iterator {

    private List<Object> list = null;
    private int index = -1;

    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object first() {
        index = 0;
        return list.get(index);
    }

    @Override
    public Object next() {
        Object obj = null;
        if (hasNext()) {
            obj = list.get(++index);
        }
        return obj;
    }

    @Override
    public boolean hasNext() {
        if (index < list.size() - 1) {
            return true;
        }
        return false;
    }
}



interface Aggregate {

    void add(Object obj);

    void remove(Object obj);

    Iterator getIterator();

}

class ConcreteAggregate implements Aggregate{

    private List<Object> list = new ArrayList<>();

    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    @Override
    public Iterator getIterator() {
        return new ConcreteIterator(list);
    }
}

public class IteratorPattern {

    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("mhn");
        aggregate.add("nhh");
        aggregate.add("gy");
        Iterator iterator = aggregate.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
