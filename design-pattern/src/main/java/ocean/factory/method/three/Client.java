package ocean.factory.method.three;

import ocean.factory.method.three.factory.Factory;
import ocean.factory.method.three.factory.FileLoggerFactory;

/**
 * 工厂方法的隐藏
 * 有时候，为了进一步简化客户端的使用，还可以对客户端隐藏工厂方法，
 * 此时，在工厂类中将直接调用产品类的业务方法，
 * 客户端无须调用工厂方法创建产品，
 * 直接通过工厂即可使用所创建的对象中的业务方法。
 *
 * @author yancy
 * @date 2019/6/25
 */
public class Client {
    public static void main(String[] args) {
        //new FileLoggerFactory()可以配置到配置文件中，用到Java反射机制与配置文件读取
        Factory factory = new FileLoggerFactory();
        factory.writeLog();
    }
}
