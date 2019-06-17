package knowledge.clone;

/**
 * 实现浅克隆
 * <p>
 * Q：如何复制一个对象？
 * A:
 * 1.对象的类实现Cloneable接口
 * 2.重写clone方法
 * 3.调用父类的clone方法，实现对象的浅复制
 * <p>
 * 对象的复制
 * <p>
 * 基本数据类型可以用浅克隆
 * 非基本数据类型要用深克隆
 * <p>
 * 浅复制与深复制的区别
 * 浅复制：被复制对象的所有变量的值与原来一样，所有对象的引用让指向原来的对象
 * 深复制：被复制对象的所有变量的值与原来一样，所有对象的引用都指向被复制的新对象
 * <p>
 * 浅克隆会创建一个新的引用指向原来的对象
 * 深克隆会创建一个新的对象和一个新的引用
 *
 * @author yancy
 * @date 2019/6/17
 */
public class ShallowClone implements Cloneable {
    int age;

    @Override
    protected ShallowClone clone() throws CloneNotSupportedException {
        return (ShallowClone) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ShallowClone shallowClone = new ShallowClone();
        ShallowClone nShallowClone = shallowClone.clone();
        System.out.println(nShallowClone == shallowClone);

    }
}