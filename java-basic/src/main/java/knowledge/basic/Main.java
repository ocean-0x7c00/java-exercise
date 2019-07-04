package knowledge.basic;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Collector定义
 * 理解Collector几个函数
 * https://www.cnblogs.com/woshimrf/p/java8-learn-collector.html
 *
 * @author ocean
 */
public class Main {
    public static void main(String[] args) {
        List list = Lists.newArrayList(
                Lists.newArrayList(1, 2, 3),
                Lists.newArrayList(4, 5, 6)
        );
        System.out.println();
        list.stream().collect(new C());

        list.stream().collect(new Collector() {
            @Override
            public Supplier supplier() {
                return null;
            }

            @Override
            public BiConsumer accumulator() {
                return null;
            }

            @Override
            public BinaryOperator combiner() {
                return null;
            }

            @Override
            public Function finisher() {
                return null;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return null;
            }
        });

        list.stream().collect(Collectors.toList());
    }
}
