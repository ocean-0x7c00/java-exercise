package ocean.creational.factory.method.one.factory;


import ocean.creational.factory.method.one.product.DatabaseLogger;
import ocean.creational.factory.method.one.product.Logger;

/**
 * 数据库日志记录器工厂类：具体工厂
 *
 * @author yancy
 * @date 2019/6/25
 */
public class DatabaseLoggerFactory implements Factory {
    @Override
    public Logger createLogger() {
        //连接数据库，代码省略
        //创建数据库日志记录器对象
        Logger logger = new DatabaseLogger();
        //初始化数据库日志记录器，代码省略
        return logger;
    }
}
