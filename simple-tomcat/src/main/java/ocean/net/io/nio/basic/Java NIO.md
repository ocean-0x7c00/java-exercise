# Java NIO

## 通道和缓冲区
通道 和 缓冲区 是 NIO 中的核心对象，几乎在每一个 I/O 操作中都要使用它们。

什么是缓冲区
缓冲区的本质是一个数组，还提供能对数组的结构化访问。存放写入或读出的数据。所有数据的读取都用缓冲区来处理。
不再一个字节或一个字符那样读或写

缓冲区的类型
对于每一种基本 Java 类型都有一种缓冲区类型
* ByteBuffer
* CharBuffer
* ShortBuffer
* IntBuffer
* LongBuffer
* FloatBuffer
* DoubleBuffer


什么是通道
类似IO中的流(stream)，数据传输的通道。
通道和流的重要区别
通道是双向的。可以读、可以写或同时读写
流是单向的。读写各对应一个流（InputStream和OutputStream）

## NIO中的读写操作
写数据时，先创建一个缓冲区，将数据写入，然后再用通道传输
读数据时，先创建一个缓冲区，读入数据，然后再用通道传输

读取文件涉及三个步骤：

* 从 FileInputStream 获取 Channel
* 创建 Buffer
* 将数据从 Channel 读到 Buffer 中。

写文件涉及三个步骤：

* 从 FileOutputStream 获取 Channel
* 创建 Buffer
* 将数据从Buffer写入到channel中。


## 参考
[NIO 入门-IBM](https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html)

[JAVA NIO学习系列](https://www.cnblogs.com/pony1223/p/8138233.html)

[Socket Programming in Java](https://www.geeksforgeeks.org/socket-programming-in-java/)

[如何去分析开源的代码，例如tomcat?](https://www.zhihu.com/question/20054992)

[tomcat源码分析(第一篇 从整体架构开始)](https://juejin.im/post/5ae170ea51882567244daef5)

[tomcat源码分析(第二篇 tomcat启动过程详解)](https://juejin.im/post/5af176196fb9a07ac90d2ac8)

[关于亲密关系](https://www.jianshu.com/p/2746aa7689ee)

[漫话：如何给女朋友解释什么是Linux的五种IO模型](https://mp.weixin.qq.com/s?__biz=Mzg3MjA4MTExMw==&mid=2247484746&idx=1&sn=c0a7f9129d780786cabfcac0a8aa6bb7&source=41#wechat_redirect)
[BIO NIO AIO](https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/BIO-NIO-AIO.md#3-aio-asynchronous-io)
