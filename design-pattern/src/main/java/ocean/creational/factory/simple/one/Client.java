package ocean.creational.factory.simple.one;

import ocean.creational.factory.simple.one.factory.ChartFactory;
import ocean.creational.factory.simple.one.product.Chart;

/**
 * 在创建具体Chart对象时，每更换一个Chart对象都需要修改客户端代码中静态工厂方法的参数，
 * 客户端代码将要重新编译，这对于客户端而言，违反了“开闭原则”
 * <p>
 * 如何处理？
 * 解决方案：静态工厂方法的参数存储在XML或properties格式的配置文件中
 *
 *
 *
 * @author yancy
 * @date 2019/6/25
 */
public class Client {
    public static void main(String[] args) {
        Chart chart = ChartFactory.createChart("line");
        chart.display();
    }
}
