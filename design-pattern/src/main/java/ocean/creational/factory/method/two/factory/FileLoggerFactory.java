package ocean.creational.factory.method.two.factory;

import ocean.creational.factory.method.two.product.FileLogger;
import ocean.creational.factory.method.two.product.Logger;

/**
 * 文件日志记录器工厂类：具体工厂
 *
 * @author yancy
 * @date 2019/6/25
 */
public class FileLoggerFactory implements Factory {
    @Override
    public Logger createLogger() {
        System.out.println("使用默认方式连接数据库");
        Logger logger = new FileLogger();
        System.out.println("初始化数据库日志记录器");
        return logger;
    }

    @Override
    public Logger createLogger(String args) {
        System.out.println("使用参数args作为连接字符串来连接数据库");
        Logger logger = new FileLogger();
        System.out.println("初始化数据库日志记录器");
        return logger;
    }

    @Override
    public Logger createLogger(Object object) {
        System.out.println("使用封装在参数obj中的连接字符串来连接数据库");
        Logger logger = new FileLogger();
        System.out.println("使用封装在参数obj中的数据来初始化数据库日志记录器");
        return logger;
    }
}
