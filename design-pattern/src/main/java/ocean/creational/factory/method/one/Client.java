package ocean.creational.factory.method.one;

import ocean.creational.factory.method.one.factory.Factory;
import ocean.creational.factory.method.one.factory.FileLoggerFactory;

/**
 * 简单工厂模式存在两个问题
 * 1.在新增加产品的时候，需要修改静态工厂类，违反了开闭原则
 * 2.简单工厂类中含有大量的if..else..语句，导致测试和维护难度较大
 * 3.所有产品都有一个工厂创建，具体产品与工厂类之间的耦合度高，严重影响了系统的灵活性和扩展性
 * <p>
 * 如何在新增产品的时候不影响静态工厂类中的已有代码呢？
 * 使用工厂方法模式，针对不同的产品提供不同的工厂
 * <p>
 * 在实际使用时，具体工厂类在实现工厂方法时除了创建具体产品对象之外，
 * 还可以负责产品对象的初始化工作以及一些资源和环境配置工作，例如连接数据库、创建文件等
 * 在客户端代码中，只需关心工厂类即可，不同的具体工厂可以创建不同的产品，
 * <p>
 * <p>
 * <p>
 * 情景
 * 日志记录器的设计
 * 开发一个系统运行日志记录器(Logger)，该记录器可以通过多种途径保存系统的运行日志，
 * 如通过文件记录或数据库记录，用户可以通过修改配置文件灵活地更换日志记录方式。
 * 在设计各类日志记录器时，发现需要对日志记录器进行一些初始化工作，初始化参数的设置过程较为复杂，
 * 而且某些参数的设置有严格的先后次序，否则可能会发生记录失败。
 * 如何封装记录器的初始化过程并保证多种记录器切换的灵活性是开发人员面临的一个难题。
 *
 * @author yancy
 * @date 2019/6/25
 */
public class Client {
    public static void main(String[] args) {
        //new FileLoggerFactory()可以配置到配置文件中，用到Java反射机制与配置文件读取
        Factory factory = new FileLoggerFactory();
        factory.createLogger().writeLog();
    }
}
