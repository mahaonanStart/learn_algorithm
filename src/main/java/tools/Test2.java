package tools;

import com.alibaba.fastjson.JSON;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Date;

/**
 * @Author: M˚Haonan
 * @Date: 2020-01-13 20:03
 * @Description:
 */
public class Test2 {

    public static void main(String[] args) {
//        DateTime start = new DateTime("2020-01-02");
//        DateTime end = new DateTime("2020-01-05");
//        Period p = new Period(start, end, PeriodType.days());
//        int days = p.getDays();
//        System.out.println(days);
//        Interval i = new Interval(start, end);
//        boolean contained = i.contains(new DateTime("2012-03-01"));
        String str = "{\"session_key\":\"I2RcNLe3xltUbXJTc2ZlQg==\",\"openid\":\"oBqtH42mg1UhuG9hk70eQ0mp88sU\"}";
        boolean valid = JSON.isValid(str);
        System.out.println(valid);
    }
}
