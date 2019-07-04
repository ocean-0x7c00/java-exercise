package ocean.inner;

/**
 * 思路：
 * 从三个角度（内部类和外部类），理清楚下面的关系
 * a.变量（成员变量和静态变量）和方法（成员方法和静态方法）的定义
 * b.调用关系
 * 变量之间的调用
 * 方法之间的调用
 * 方法中调用变量
 * c.实例化的先后顺序
 * <p>
 * <p>
 * 内部类有四种
 * a.静态内部类
 * b.成员内部类
 * c.局部内部类
 * d.匿名内部类
 * <p>
 * 局部内部类
 *
 * @author yancy
 * @date 2019/6/18
 */
public class OutClass3 {
    static int outA = 10;
    int outB = 1;

    public void m() {

        final int aInner = 11;
        class Inner {
            int a = 10;

            public void setA() {
                outA = 11;
                outB = 11;
                System.out.println(aInner);
            }
        }

        Inner inner = new Inner();
    }
}
