package main.java.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * @Author: M˚Haonan
 * @Date: 2019-05-14 15:42
 * @Description: Optional类的测试
 */
class Company{
    private List<String> persons;

    /**
     * @return the persons
     */
    public List<String> getPersons() {
        return persons;
    }

    /**
     * @param persons the persons to set
     */
    public void setPersons(List<String> persons) {
        this.persons = persons;
    }
    
}

public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("hello");
        //不推荐使用这种
        if (optional.isPresent()){
            System.out.println(optional.get());
        }
        //推荐使用
        //如果为空不输出，不为空输出
        optional.ifPresent(item -> System.out.println(item));
        //如果为空输出other
        optional.orElse("world");
        //如果为空输出
        optional.orElseGet(() -> "world");
        List<String> list = Arrays.asList("mhn", "nhh");
        Company company = new Company();
        company.setPersons(list);
        Optional<Company> cOptional = Optional.ofNullable(company);
        System.out.println(cOptional.map(c -> c.getPersons()).orElse(Collections.EMPTY_LIST));
    }
}
