package ocean.factory.simple.one.excise;

/**
 * @author yancy
 * @date 2019/6/25
 */
public class Square implements Shape {
    public Square() {
        System.out.println("create Square");
    }

    @Override
    public void draw() {
        System.out.println("Square draw...");
    }

    @Override
    public void erase() {
        System.out.println("Square erase...");
    }
}
