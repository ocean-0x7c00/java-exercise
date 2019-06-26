package ocean.factory.method.one.factory;

import ocean.factory.method.one.product.FileLogger;
import ocean.factory.method.one.product.Logger;

/**
 * 文件日志记录器工厂类：具体工厂
 *
 * @author yancy
 * @date 2019/6/25
 */
public class FileLoggerFactory implements Factory {
    @Override
    public Logger createLogger() {
        //创建文件日志记录器对象
        Logger logger = new FileLogger();
        //创建文件，代码省略
        return logger;
    }
}
