package ocean.factory.method.two.product;

/**
 * 数据库日志记录器：具体产品
 *
 * @author yancy
 * @date 2019/6/25
 */
public class DatabaseLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("DatabaseLogger write...");
    }
}
