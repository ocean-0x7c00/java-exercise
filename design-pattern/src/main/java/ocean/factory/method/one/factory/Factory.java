package ocean.factory.method.one.factory;

import ocean.factory.method.one.product.Logger;

/**
 * 日志记录器工厂接口：抽象工厂
 *
 * @author yancy
 * @date 2019/6/25
 */
public interface Factory {
    Logger createLogger();
}
