package ocean.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 在使用泛型类的时候，应该为泛型类传入类型实参
 *
 * @author yancy
 * @date 2019/8/14
 */
public class GenericTest<M> {

    //泛型不能用在静态初始化块、变量和方法上
    {
        M e;
    }

    M b;

    M method() {
        M e = (M) new A();
        return e;
    }

//    static {
//        M s;
//    }
//
//    static M a;
//
//    static M staticMethod() {
//        return M;
//    }


    public static void main(String[] args) {


        List<String> strings = new ArrayList();
        strings.add("1");
        List<Object> objects = new ArrayList();
        objects.add(1);

        // 会有不同的list传给test方法，list的对象是不同的，要如何定义方法test的形参list
        test(strings);
        test(objects);

        A a = new A();
        a.name = 1;
        B b = new B();
        b.name = 1;
        C c = new C();
        c.num = 1;
        c.num = 1;
        //已经在定义类型形参是设定了上限
        //class C<T extends Number> {T num;}
//        c.num = "12";
        Apple apple = new Apple();
        List<Apple> list = new ArrayList();
        list.add(apple);
        test1(list);

        GenericTest.sayHello(1);

    }

    public static void test1(List<? extends Apple> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * list中会存放不同类型的对象，要如何定义list
     *
     * @param list
     */
    public static void test(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }


    static <K> K sayHello(K m) {
        return m;
    }


}

//错误
//class A extends Apple<T> {
//
//}

/**
 * 使用Apple<Integer>,则Apple中的所有泛型T，都被替换为Integer
 */
class A extends Apple<Integer> {

}

/**
 * 如果Apple不使用泛型，则Apple中T在编译时期指定
 */
class B extends Apple {

}

class C<T extends Number> {
    T num;
}

class Apple<T> {
    T name;

}
