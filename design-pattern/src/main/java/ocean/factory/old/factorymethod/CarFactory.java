package ocean.factory.old.factorymethod;

/**
 * @author yancy
 * @date 2019/6/24
 */
public class CarFactory {
    public Car createCar() {
        System.out.println("a car created!");
        return new Car();
    }
}
