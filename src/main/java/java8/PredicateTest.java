package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-08 23:03
 * @Description: Predicate接口测试
 */
public class PredicateTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        list.stream().filter(integer -> (integer & 1) == 1).forEach(System.out::println);
        PredicateTest test = new PredicateTest();
//        main.java.algorithm.test.conditionFilter(list, item -> (item & 1) == 0);
       // main.java.algorithm.test.conditionFilter(list, integer -> integer < 4);
        test.conditionFilter(list, integer -> integer > 5, integer -> (integer & 1) ==0);
        
    }

    public void conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        for (Integer integer : list) {
            if (predicate.test(integer)){
                System.out.println(integer);
            }
        }
    }
	
	public void conditionFilter(List<Integer> list, Predicate<Integer> p1, Predicate<Integer> p2){
		for(Integer integer: list){
			if (p1.and(p2).test(integer)){
			    System.out.println(integer);
            }
		}
		
	}
}
