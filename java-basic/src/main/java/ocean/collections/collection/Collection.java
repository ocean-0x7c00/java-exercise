package ocean.collections.collection;

/**
 * @author yancy
 * @date 2019/7/4
 */
public interface Collection<E> {
    /**
     * @return
     */
    int size();

    /**
     * @return
     */
    boolean isEmpty();


    /**
     * @param o
     * @return
     */
    boolean contains(Object o);

    /**
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * @param o
     * @return
     */
    boolean remove(Object o);

    /**
     *
     */
    void clear();
}
