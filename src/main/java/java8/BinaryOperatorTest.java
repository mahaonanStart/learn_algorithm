package java8;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-13 22:16
 * @Description: BinaryOperator的测试类
 */
public class BinaryOperatorTest {

    public static void main(String[] args) {
		BinaryOperatorTest bot = new BinaryOperatorTest();
		System.out.println(bot.compute(2, 3, (a, b) -> a + b));
		System.out.println(bot.compute(2, 3, (a, b) -> a - b));
		System.out.println(bot.getShort("mhn", "niuhonghong", (a, b) -> a.length() - b.length()));
		
    }
	
	public int compute(int a, int b, BinaryOperator<Integer> bo){
		return bo.apply(a, b);
	}
	
	public String getShort(String str1, String str2, Comparator<String> comparator){
		return BinaryOperator.minBy(comparator).apply(str1, str2);
	}
}
