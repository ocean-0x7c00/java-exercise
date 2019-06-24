package ocean.factory.factorymethod;

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
public class Main {
    public static void main(String[] args) {
        /**
         * 有新的扩展（有新的交通工具），如何灵活指定交通工具？
         * 将交通工具进行抽象，所有新来的交通工具，实现这个接口类
         *
         * 将产生对象的过程交给工厂
         */
        Moveable m = new CarFactory().createCar();
        m.go();


    }
}
