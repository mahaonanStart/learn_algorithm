package java8;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @Author: M˚Haonan
 * @Date: 2019-07-26 16:20
 * @Description: 自定义收集器实现
 */
public class MySetCollector<T> implements Collector<T, Set<T>, Set<T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked！");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        return null;
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
