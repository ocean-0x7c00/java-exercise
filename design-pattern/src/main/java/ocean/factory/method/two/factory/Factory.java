package ocean.factory.method.two.factory;

import ocean.factory.method.two.product.Logger;

/**
 * 日志记录器工厂接口：抽象工厂
 *
 * @author yancy
 * @date 2019/6/25
 */
public interface Factory {
    Logger createLogger();

    Logger createLogger(String args);

    Logger createLogger(Object object);
}
