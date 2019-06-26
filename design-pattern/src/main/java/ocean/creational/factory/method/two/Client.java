package ocean.creational.factory.method.two;

import ocean.creational.factory.method.two.factory.Factory;
import ocean.creational.factory.method.two.factory.FileLoggerFactory;

/**
 * 重载工厂方法
 * 通过多种方法来初始化工厂创建出来的对象
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
