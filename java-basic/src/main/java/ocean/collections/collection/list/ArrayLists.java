package ocean.collections.collection.list;

import java.util.Collection;

/**
 * 问题
 * Q1:为什么要加transient？
 * Q2:Collection<? extends E>是什么意思，泛型怎么用？
 * Q3:toArray怎么实现的？
 * Q4:ArrayList自动扩容实现的过程是怎样的?
 * Q5:ArrayList中add，set实现的过程
 *
 * @author yancy
 * @date 2019/7/4
 */
public class ArrayLists<E> {

    /**
     * Q1:为什么要加transient？
     */
    Object[] elementData;

    /**
     * elementData.length
     */
    private int size;

    public ArrayLists(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[]{};
        } else {
            throw new RuntimeException("Illegal Capacity " + initialCapacity);
        }
    }

    /**
     * Q2:Collection<? extends E>是什么意思，泛型怎么用？
     * Q3:toArray怎么实现的？
     * Q4:ArrayList自动扩容实现的过程是怎样的?
     * Q5:ArrayList中add，set实现的过程
     *
     * @param c
     */
    public ArrayLists(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {

        } else {
            elementData = new Object[]{};
        }
    }

    public ArrayLists() {
    }
}
