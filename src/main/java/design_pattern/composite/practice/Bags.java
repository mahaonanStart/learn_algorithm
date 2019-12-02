package main.java.design_pattern.composite.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-15 18:27
 * @Description: 树枝构件 袋子
 */
public class Bags implements Product{

    private String name;
    private List<Product> bags = new ArrayList<>();

    public Bags(String name) {
        this.name = name;
    }

    public void add(Product product) {
        bags.add(product);
    }

    public void remove(Product product) {
        bags.remove(product);
    }

    public Product getChild(int i) {
        return bags.get(i);
    }

    @Override
    public float calculate() {
        float s = 0;
        for (Product bag : bags) {
            s += bag.calculate();
        }
        return s;
    }

    @Override
    public void show() {
        for (Product bag : bags) {
            bag.show();
        }
    }
}
