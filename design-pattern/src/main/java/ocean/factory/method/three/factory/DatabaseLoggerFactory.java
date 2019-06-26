package ocean.factory.method.three.factory;


import ocean.factory.method.three.product.FileLogger;
import ocean.factory.method.three.product.Logger;

/**
 * 数据库日志记录器工厂类：具体工厂
 *
 * @author yancy
 * @date 2019/6/25
 */
public class DatabaseLoggerFactory extends Factory {
    @Override
    public Logger createLogger() {
        System.out.println("使用默认方式连接数据库");
        Logger logger = new FileLogger();
        System.out.println("初始化数据库日志记录器");
        return logger;
    }
}
