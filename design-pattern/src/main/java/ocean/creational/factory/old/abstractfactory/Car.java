package ocean.creational.factory.old.abstractfactory;

import ocean.creational.factory.old.factorymethod.Moveable;

/**
 * @author yancy
 * @date 2019/6/24
 */
public class Car implements Moveable {
    @Override
    public void go() {
        System.out.println("Car go .....");
    }
}
