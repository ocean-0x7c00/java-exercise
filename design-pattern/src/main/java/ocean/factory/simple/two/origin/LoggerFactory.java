package ocean.factory.simple.two.origin;

/**
 * @author yancy
 * @date 2019/6/25
 */
public class LoggerFactory {
    public static Logger createLogger(String args) {
        if (args.equalsIgnoreCase("db")) {
            //连接数据库，代码省略
            //创建数据库日志记录器对象
            Logger logger = new DatabaseLogger();
            //初始化数据库日志记录器，代码省略
            return logger;
        } else if (args.equalsIgnoreCase("file")) {
            //创建日志文件
            //创建文件日志记录器对象
            Logger logger = new FileLogger();
            //初始化文件日志记录器，代码省略
            return logger;
        } else {
            return null;
        }
    }
}
