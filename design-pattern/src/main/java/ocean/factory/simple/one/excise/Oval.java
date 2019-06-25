package ocean.factory.simple.one.excise;

/**
 * @author yancy
 * @date 2019/6/25
 */
public class Oval implements Shape {
    public Oval() {
        System.out.println("create Oval");
    }

    @Override
    public void draw() {
        System.out.println("Oval draw...");
    }

    @Override
    public void erase() {
        System.out.println("Oval erase...");
    }
}
