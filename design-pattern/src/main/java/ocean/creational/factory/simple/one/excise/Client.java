package ocean.creational.factory.simple.one.excise;

/**
 * 使用简单工厂模式
 * 设计一个可以创建不同几何形状（如圆形、方形和三角形等）的绘图工具，
 * 每个几何图形都具有绘制draw()和擦除erase()两个方法，
 * 要求在绘制不支持的几何图形时，提示一个UnSupportedShapeException。
 *
 * @author yancy
 * @date 2019/6/25
 */
public class Client {
    public static void main(String[] args) {
        Shape shape = ShapeFactory.createShape("oval");
        shape.draw();
        shape.erase();
    }
}
