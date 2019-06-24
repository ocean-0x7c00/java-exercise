package ocean.factory.factorymethod;

/**
 * @author yancy
 * @date 2019/6/24
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 有新的扩展（有新的交通工具），如何灵活指定交通工具？
         * 将交通工具进行抽象，所有新来的交通工具，实现这个接口类
         */
        Car car = new Car();
        car.go();

        Plane plane = new Plane();
        plane.go();


    }
}
