package ocean.behavioral.iterator.one;

/**
 * @author yancy
 * @date 2019/6/26
 */
public class ConcreteAggregate implements Aggregate {
    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }
}
