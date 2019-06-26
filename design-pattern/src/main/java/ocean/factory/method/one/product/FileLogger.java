package ocean.factory.method.one.product;

/**
 * 文件日志记录器：具体产品
 *
 * @author yancy
 * @date 2019/6/25
 */
public class FileLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("FileLogger write...");
    }
}
