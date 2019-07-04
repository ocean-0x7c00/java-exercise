package knowledge.basic;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class C implements Collector {
    @Override
    public Supplier supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List, List> accumulator() {
        return List::addAll;
    }

    @Override
    public BinaryOperator combiner() {
        return null;
    }

    @Override
    public Function<List, List> finisher() {
        return (i)->i;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Collector.Characteristics.IDENTITY_FINISH);
    }
}
