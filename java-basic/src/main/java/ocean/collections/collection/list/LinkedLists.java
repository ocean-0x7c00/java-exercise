package ocean.collections.collection.list;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * 核心问题
 * 1.LinkedList如何实现的，用到的数据结构是什么
 * 2.LinkedList是否有容量限制
 * 3.LinkedList的新增、查找、删除操作的实现
 * 4.Java7和Java8对于ArrayList的实现有何不同
 *
 * @author yancy
 * @date 2019/7/4
 */
public class LinkedLists {
    public static void main(String[] args) {
        LinkedList list = new LinkedList<>();
        list.add(1);
        System.out.println();
        HashMap<String, String> map = new HashMap();
        map.put("name", "Lee");

        Hashtable hashtable = new Hashtable();
        hashtable.put("", "");
    }
}
