import java.math.BigDecimal;

/**
 * @Author: M˚Haonan
 * @Date: 2019-12-26 15:33
 * @Description:
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println((int)(2599.99 * 100));
        BigDecimal bg = new BigDecimal(2599.99 + "");
        BigDecimal decimal = bg.multiply(new BigDecimal(100));
        System.out.println(decimal.intValue());
    }
}
