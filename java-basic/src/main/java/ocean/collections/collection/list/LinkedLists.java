package ocean.collections.collection.list;

import java.util.LinkedList;

/**
 * 核心问题
 * 1.LinkedList如何实现的，用到的数据结构是什么?
 * 数据结构：双向链表
 * <p>
 * 2.LinkedList是否有容量限制
 * Jvm容量的限制
 * <p>
 * 3.LinkedList的新增、查找、删除操作的实现
 * 新增 add()是在list的尾部插入
 * 在指定位置新增：首先要先找到指定的节点，再插入
 * 查找元素：node(int index)，查找index位置非null的元素。如果index<(size/2)，则从头开始找
 * 查找指定位置元素
 * <p>
 * 删除
 * <p>
 * <p>
 * 4.LinkedList如何实现随机查找
 * 使用一个变量记录下标
 * <p>
 * 4.Java7和Java8对于ArrayList的实现有何不同
 *
 * @author yancy
 * @date 2019/7/4
 */
public class LinkedLists {
    public static void main(String[] args) {
        LinkedList list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("4");
        list.add("5");

        System.out.println(list.get(1));
        System.out.println(list.indexOf("4"));
        System.out.println(list.lastIndexOf("4"));
    }
}
