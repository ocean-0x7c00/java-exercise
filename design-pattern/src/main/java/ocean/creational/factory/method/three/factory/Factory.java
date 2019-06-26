package ocean.creational.factory.method.three.factory;

import ocean.creational.factory.method.three.product.Logger;

/**
 * 日志记录器工厂接口：抽象工厂
 *
 * @author yancy
 * @date 2019/6/25
 */
public abstract class Factory {
    public void writeLog() {
        Logger logger = this.createLogger();
        logger.writeLog();

    }

    abstract Logger createLogger();

}
