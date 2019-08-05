package ocean.log4j_01;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 使用日志系统 log4j
 * <p>
 * 1.只要log4j的配置文件名为log4j.properties且该配置文件在类根路径(resources)下，
 * 则程序可自动加载该配置文件，不用写任何代码，log4j就会初试化
 *
 * 2.可以通过PropertyConfigurator.configure("");
 * 语句来加载自定义命名的配置文件（包括log4j.properties），但是得明确指定配置文件的放置目录
 *
 *
 * @author ocean
 * @date 2019/8/5
 */
public class Client {
    /**
     * 得到记录器
     */
    private static Logger log = Logger.getLogger(Client.class);

    public static void main(String[] args) {

        //在src下使用全路径
        //PropertyConfigurator.configure("/Users/ocean/Documents/java-exercise/java-log-framework/src/main/java/ocean/log4j_01/log4j.properties");

//        PropertyConfigurator.configure("../conf/log4j.properties");

        log.info("info");
        log.error("error");
        log.warn("warn");
        log.debug("debug");
    }
}
