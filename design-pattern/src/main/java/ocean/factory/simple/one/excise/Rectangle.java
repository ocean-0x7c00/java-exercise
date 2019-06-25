package ocean.factory.simple.one.excise;

/**
 * @author yancy
 * @date 2019/6/25
 */
public class Rectangle implements Shape {
    public Rectangle() {
        System.out.println("create Rectangle");
    }

    @Override
    public void draw() {
        System.out.println("Rectangle draw...");
    }

    @Override
    public void erase() {
        System.out.println("Rectangle erase...");
    }
}
