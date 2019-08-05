package ocean.log4j_01;


import org.apache.log4j.Logger;

/**
 * @author ocean
 * @date 2019/8/5
 */
public class Client {
    private static Logger logger = Logger.getLogger(Client.class);

    public static void main(String[] args) {
        logger.info("hello");
    }
}
