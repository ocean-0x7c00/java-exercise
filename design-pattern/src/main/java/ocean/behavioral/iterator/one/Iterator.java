package ocean.behavioral.iterator.one;

/**
 * @author yancy
 * @date 2019/6/26
 */
public interface Iterator {
    /**
     * 将游标指向第一个元素
     */
    void first();

    /**
     * 将游标指向下一个元素
     */
    void next();

    /**
     * 判断是否存在下一个元素
     *
     * @return
     */
    boolean hasNext();

    /**
     * 获取游标指向的当前元素
     *
     * @return
     */
    Object currentItem();
}
