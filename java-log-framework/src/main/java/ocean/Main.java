package ocean;

/**
 * 1.Java日志发展历程
 * <p>
 * [Java 日志系统梳理](http://patchouli-know.com/2017/04/08/java-log/)
 * <p>
 * Apache log4j
 * --> JUL(Java.util.logging,java 1.4引入，影响：导致java日志体系混乱)  日志系统
 * --> Apache commons-logging (简称JCL，它不是一个日志打印系统，通用日志接口定义，起到适配和连接的作用,jul_02,log4j是具体的实现)
 * --> Slf4j(通用日志接口，logback是官方的实现日志系统；slf4j为了解决jcl动态加载实现类引发的问题)
 * --> Log4j2 同时也支持slf4j和jcl
 * <p>
 * 2.Java日志框架有哪些？
 * Apache commons-logging 和 Slf4j
 * 日志框架：只是一种日志接口，不负责具体的日志输出实现，而是通过配置来灵活的选择具体的日志系统。例如 jcl、slf4j 就是日志框架，它们只提供了接口，没有提供具体的日志系统实现；
 * 日志系统：真正的日志实现，如 log4j、jul、logback 与 log4j2 都是具体的日志系统实现。
 * <p>
 * 日志系统有
 * log4j、JUL、logback和log4j2
 * <p>
 * 3.如何配置日志系统
 * 配置Logger
 * 配置Appender
 * 配置Layout
 * <p>
 * 4.日志框架的工作原理
 *
 * @author ocean
 * @date 2019/8/5
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(
        );
    }
}
