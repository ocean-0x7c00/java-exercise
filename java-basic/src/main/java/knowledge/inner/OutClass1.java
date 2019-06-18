package knowledge.inner;

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
 * 静态内部类
 *
 * @author yancy
 * @date 2019/6/18
 */
public class OutClass1 {
    static int aStatic = 10;
    int bOuter = 20;


    private static int ssa() {
        return 1;
    }

    private int s21() {
        return 1;
    }

    static class InnerClass {
        static int aInner = 10;
        int bInner = 20;

        public static int m() {
            aStatic = 10;
            ssa();
            new OutClass1().s21();
            return 0;
        }

        public int s() {
            return 11;
        }
    }

    public static void main(String[] args) {
        //静态内部类的创建方式
        OutClass1.InnerClass innerClass = new OutClass1.InnerClass();

    }
}
