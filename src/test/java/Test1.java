import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tools.ant.taskdefs.condition.Or;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: M˚Haonan
 * @Date: 2019-12-26 15:33
 * @Description:
 */
@Data
@AllArgsConstructor
class Order {

    private String name;

    private String orderSn;


    public String getNameId() {
        return name + orderSn;
    }

}

public class Test1 {

    public static void main(String[] args) {
        Order a1 = new Order("zs", "E123");
        Order a2 = new Order("zs", "E123");
        Order a3 = new Order("zs", "E12412");
        Order a4 = new Order("zs", "E345123");
        Order b1 = new Order("ls", "E123");
        Order b2 = new Order("ls", "E12412");
        Order b3 = new Order("ls", "E345123");
        Order c1 = new Order("ww", "E123");
        Order c2 = new Order("ww", "E345");
        List<Order> list = new ArrayList<>();
        list.add(a1);
        list.add(b1);
        list.add(c2);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(b2);
        list.add(c1);
        list.add(b3);
        Map<String, List<Order>> orderMap = new HashMap<>();
        List<Order> subOrder;
        for (Order order : list) {
            subOrder = orderMap.computeIfAbsent(order.getName(), key -> new ArrayList<>());
            subOrder.add(order);
        }
        System.out.println(orderMap);
    }
}
