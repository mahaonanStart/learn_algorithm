package design_pattern.flyweight.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-12 16:05
 * @Description: 享元工厂角色
 */
public class FlyweightFactory {
    private Map<String, Flyweight> flyweights = new HashMap<>();


    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = flyweights.get(key);
        if (flyweight != null) {
            System.out.println("具体享元" + key + "已经存在，被成功获取");
        }else {
            flyweight = new ConcreteFlyweight(key);
            flyweights.put(key, flyweight);
        }
        return flyweight;
    }
}
