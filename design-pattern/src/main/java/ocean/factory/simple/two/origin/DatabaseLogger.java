package ocean.factory.simple.two.origin;

/**
 * @author yancy
 * @date 2019/6/25
 */
public class DatabaseLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("DatabaseLogger write...");
    }
}
