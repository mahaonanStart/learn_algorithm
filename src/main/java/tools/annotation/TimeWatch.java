package tools.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-17 11:14
 * @Description: 时间统计注解，在所需要统计的方法上使用, 只能统计某个方法的耗时
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface TimeWatch {

    /**
     * 是否打印日志,默认是
     * @return
     */
    boolean print() default true;

    /**
     * 时间统计单位，默认毫秒
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;



}
