package ocean.collections.collection.list;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author yancy
 * @date 2019/7/9
 */
public class Client {
    public static void main(String[] args) {
//        m1();
//        m2();
        ArrayList list = new ArrayList();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        },"t1").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }
        },"t2").start();
        System.out.println(list.size());
        LinkedList linkedList = new LinkedList();
    }

    public static void m1() {
        ArrayList list = new ArrayList(10000000);
        Long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        Long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    public static void m2() {
        ArrayList list = new ArrayList();
        Long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        Long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

    }
}
