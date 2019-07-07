package ocean.collections.map;

import java.util.HashMap;
import java.util.Objects;

/**
 * 重写equals方法时，也要重写hashcode方法
 * hashcode对象在内存中的地址
 * <p>
 * https://blog.csdn.net/qq_38182963/article/details/78940047
 *
 * @author yancy
 * @date 2019/7/7
 */
public class MyTest {
    static class Person {
        int idCard;
        String name;

        public Person(int idCard, String name) {
            this.idCard = idCard;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (idCard != person.idCard) return false;
            //两个对象是否等值，通过idCard来确定
            return Objects.equals(idCard, person.idCard);
        }

        @Override
        public int hashCode() {

            return Objects.hashCode(idCard);
        }
    }

    public static void main(String[] args) {
        HashMap<Person, String> map = new HashMap<Person, String>();
        Person person = new Person(1234, "乔峰");
        map.put(person, "天龙八部");
        System.out.println(person.hashCode());
        Person p = new Person(1234, "萧峰");
        System.out.println(p.hashCode());
        System.out.println("结果:" + map.get(p));
    }
}
