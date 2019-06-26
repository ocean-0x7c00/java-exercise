package ocean.creational.factory.old.factorymethod;

/**
 * 简单工厂
 * 可扩展性不好
 * <p>
 * 解决方法:
 * 可以针对每一种产品创建一个工厂
 * 仍然存在的问题
 *
 *
 * @author yancy
 * @date 2019/6/24
 */
public class SimpleVehicleFactory {
    public Car createCar() {
        //前置操作可以进行权限控制等
        return new Car();
    }

    public Plane createPlane() {
        return new Plane();
    }

    public Broom createBroom() {
        return new Broom();
    }
}
