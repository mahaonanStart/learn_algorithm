package main.java.design_pattern.composite.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-15 18:27
 * @Description: 树叶构件 商品
 */
public class Goods implements Product{
    private String name;
    private int quantity;
    private float unitPrice;

    public Goods(String name, int quantity, float unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public float calculate() {
        return quantity * unitPrice;
    }

    @Override
    public void show() {
        System.out.println(name + "(数量："+ quantity +"，单价："+ unitPrice +"元)");
    }
}
