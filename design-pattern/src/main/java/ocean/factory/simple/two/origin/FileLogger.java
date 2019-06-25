package ocean.factory.simple.two.origin;

/**
 * @author yancy
 * @date 2019/6/25
 */
public class FileLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("FileLogger write...");
    }
}
