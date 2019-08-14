# 泛型

## 一个问题

将一个对象放到Java集合中，集合会把所有的对象都当成Object来处理。
从集合中取出对象后，就需要进行强制类型转换。强制类型转换不仅使程序臃肿，而且容易引起
ClassCastException

## 什么是泛型

泛型是为了解决从集合取出对象后进行强制类型转换的问题。
泛型可以规定集合中元素的类型，在编译时检查，是否向集合中添加了
不满足要求的对象，编译器报错


## 泛型类、泛型接口、泛型方法的定义

泛型类和泛型接口可以使用```类型形参```定义泛型

```java
//静态的方法、变量和初始化块不允许使用类型形参
//泛型类，在使用泛型类的时候，应该为泛型类传入类型实参
//GenericClass<T>,T称为类型形参
class GenericClass<T>{
    T name;
    public void getName(){
       
    }
}

//泛型接口
interface GenericInterface<T>{
    //泛型方法
    T method();
    
    void mehtod1(List<T> list);
}

```

泛型方法的定义分为两种

```java
//1.定义类或接口时指定了类型形参，则类型形参在整个类中都有效
class A<T>{
    T sayHello(){
        return T;
    }
}

//2.定义类或接口时没有指定类型形参，则可以再声明方法时定义类型形参
class A{
 
    <T> T sayHello(){
        return T;
    }
}
```



## 什么是类型通配符，类型通配符的上限和类型通配符的下限

问题：将不同的对象存到方法中的集合里，这个集合要如何定义？
```java
public class GenericTest<M> {
    public static void main(String[] args) {
        List<String> strings = new ArrayList();
        strings.add("1");
        List<Object> objects = new ArrayList();
        objects.add(1);

        // 会有不同的list传给test方法，list的对象是不同的，要如何定义方法test的形参list
        test(strings);
        test(objects);

    }

    /**
     * list中会存放不同类型的对象，要如何定义list?
     * 使用类型通配符
     *
     * @param list
     */
    public static void test(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }


}
```

类型通配符的种类

* ?:表示任何类型
* <? extends Type>:表示都是Type的子类
* <? super Type>:  表示都是Type的父类

5.泛型方法和类型通配符的区别和联系
6.泛型方法与方法重写、方法重载
7.什么是类型推断
8.如何类型推断
9.什么是擦除和转换
10.泛型和数组