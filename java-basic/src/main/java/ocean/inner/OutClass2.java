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
 * 成员内部类
 *
 * @author yancy
 * @date 2019/6/18
 */
public class OutClass2 {
    static int aOut = 1;
    int bOut = 2;

    public static void m() {

    }

    public void s() {
    }

    public OutClass2() {
        System.out.println("outClass Constructor");
    }

    public class InnerClass {
        public InnerClass() {
            System.out.println("InnerClass Constructor");
        }

        int bInner = 2;

        public void s() {
            System.out.println("ssdsds");
        }
    }


    public static void main(String[] args) {
        //成员内部类的创建方式
        //只有外部类被实例化了，内部类才能被实例化
        OutClass2 outClass2 = new OutClass2();
        OutClass2.InnerClass innerClass = outClass2.new InnerClass();

        OutClass2.InnerClass innerClass1 = new OutClass2().new InnerClass();
        innerClass1.s();
    }
}
