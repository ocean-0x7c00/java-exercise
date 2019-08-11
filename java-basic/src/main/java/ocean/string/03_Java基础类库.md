# Java基础类库

## 一、交互

### 运行Java程序的参数


Java程序入口
```public static void main(String[] args)```
在JVM不会先创建对象，再调用main方法，因为main方法被```static```修饰
用户可以在使用命令```java```指定参数，参数存放在main方法的```args参数中```
格式如下
```java Main args1 args2 "args3 Hello World"....```
参数之间用空格隔开，如果参数中含有空格，用双引号括起来

### 获取键盘输入

## 二、与操作系统相关

Java可能需要获取操作系统的相关属性或执行操作系统的相关命令，
可以通过System类和Runtime类实现与操作系统的交互

### System类

1.System类是对谁的抽象

代表当前Java程序运行所在的操作系统

2.System提供了哪些功能

* 获取操作系统的环境变量```getenv()```
* 获取操作系统的属性```getProperty() getProperties()```
* 获取操作系统的当前时间```Long currentTimeMills(),Long nanoTime()```
* ```identityHashCode(Object x)```
* 提供三个方法分别代表标准输入(in)、标准输出(out)和错误输出流(err)，可以通过
```setIn()、setOut和setErr()```对标准输入、输出、错误流进行重定向
* 提供了加载文件和动态链接库的方法，如下面的例子


### Runtime类

1.Runtime类是对谁的抽象

代表虚拟机。每个Java程序都有一个与之对应的Runtime实例。应用程序不允许创建Runtime类的实例，只能
通过```getRuntim()```获取一个Runtime实例

2.Runtime提供了哪些功能

* 查询JVM相关信息
* 使用```exec```方法执行shell命令
* 使用```load(String fileName) loadLibrary(String library)```加载文件和动态链接库

## 三、常用类

1.Object类和Objects工具类

2.String、StringBuffer和StringBuilder类（与字符串相关）

* 提供了哪些方法
* 实现源码
* 内存布局
* 三者的共同点和异同点

3.Math类

* Math类的常用方法
* Random
* ThreadLocalRandom
* BigDecimal类


## 四、日期和时间类

* Date类
* Calendar类
* Java8的日期和时间类

## 五、正则表达式

## 六、国际化与格式化

* Java国际化的思路
* Java支持的国家和语言
* 国际化的示例
