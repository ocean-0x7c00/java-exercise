package ocean.factory.old.abstractfactory;

/**
 * 任意定制交通工具
 * * 继承Moveable
 * 任意定制生成过程
 * * XXXFactory.create()
 * 任意定义产品一族
 *
 * @author yancy
 * @date 2019/6/24
 */
public class Client {
    public static void main(String[] args) {

        /**
         * 交通工具、武器、食物
         * 模拟多种产品 car ak47 bread
         * 如何灵活扩展产品族
         */
        Car car = new Car();
        car.go();
        AK47 ak47 = new AK47();
        ak47.shoot();
        Bread bread = new Bread();
        bread.printName();


    }
}
