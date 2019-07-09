package ocean.collections.collection.list;

import java.util.Collection;

/**
 * 核心问题
 * 1.ArrayList如何实现的，用到的数据结构是什么
 * 使用数组实现(Object[] elementData)
 * <p>
 * 2.ArrayList如何扩容
 * 用方法ensureCapacity(size+1)
 * 如果容量不够，先尝试将oldCapacity增加到原来的1.5倍，
 * 如果新增后的容量newCapacity仍然不能满足，则使用size+1作为新数组的容量
 * 使用System.arrayCopy()方法进行数组的移动
 * <p>
 * <p>
 * 3.ArrayList的新增、查找、删除操作的实现
 * 新增：先判断数组容量是否足够，在数组最后的位置新增
 * 在指定位置新增：判断用量是否足够，找到index的位置，将index之后的数据往后移动，空出index，插入数据
 * <p>
 * 删除：从index=0开始遍历删除，找到响应的元素，置为null
 * 在指定位置删除 指定使用elementData[index]定位元素
 * <p>
 * 查找：随机访问
 * <p>
 * 4.Java7和Java8对于ArrayList的实现有何不同
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
