package ocean.behavioral.iterator.origin;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发了一套销售管理系统,需要对系统中的商品数据、客户数据等进行遍历，为了复用这些遍历代码
 * 设计了一个抽象的数据集合类AbstractObjectList，而将存储商品和客户等数据的类作为其子类
 * AbstractObjectList类的子类ProductList和CustomerList
 * 分别用于存储商品数据和客户数据。
 * <p>
 * AbstractObjectList设计缺陷
 * 1.addObject()、removeObject()等方法用于管理数据，
 * 而next()、isLast()、previous()、isFirst()等方法用于遍历数据。
 * 这将导致聚合类的职责过重，它既负责存储和管理数据，又负责遍历数据，
 * 违反了“单一职责原则”，由于聚合类非常庞大，实现代码过长，
 * 还将给测试和维护增加难度
 * <p>
 * 2.如果将抽象聚合类声明为一个接口，则在这个接口中充斥着大量方法，
 * 不利于子类实现，违反了“接口隔离原则”
 * <p>
 * 3.如果将所有的遍历操作都交给子类来实现，将导致子类代码庞大，
 * 而且必须暴露AbstractObjectList的内部存储细节，
 * 向子类公开自己的私有属性，否则子类无法实施对数据的遍历，
 * 这将破坏AbstractObjectList类的封装性
 * <p>
 * 解决方案
 * 解决方案之一就是将聚合类中负责遍历数据的方法提取出来，
 * 封装到专门的类中，实现数据存储和数据遍历分离，
 * 无须暴露聚合类的内部属性即可对其进行操作
 *
 * @author yancy
 * @date 2019/6/26
 */
public abstract class AbstractObjectList {
    private List<Object> objects;

    public AbstractObjectList(ArrayList objects) {
        this.objects = objects;
    }

    /**
     * 增加元素
     *
     * @param obj
     */
    void addObject(Object obj) {
    }

    /**
     * 删除元素
     *
     * @param obj
     */
    void removeObject(Object obj) {
    }

    /**
     * 获取所有元素
     *
     * @return
     */
    List getObjects() {
        return null;
    }

    /**
     * 移至下一个元素
     */
    void next() {

    }

    /**
     * 判断当前元素是否是最后一个元素
     *
     * @return
     */
    boolean isLast() {
        return false;
    }

    /**
     * 移至上一个元素
     */
    void previous() {

    }

    /**
     * 判断当前元素是否是第一个元素
     *
     * @return
     */
    boolean isFirst() {
        return false;
    }

    /**
     * 获取下一个元素
     *
     * @return
     */
    Object getNextItem() {
        return null;
    }

    /**
     * 获取上一个元素
     *
     * @return
     */
    Object getPreviousItem() {
        return null;
    }
}
