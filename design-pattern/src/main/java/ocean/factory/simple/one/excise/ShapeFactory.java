package ocean.factory.simple.one.excise;

/**
 * @author yancy
 * @date 2019/6/25
 */
public class ShapeFactory {
    public static Shape createShape(String type) {
        if (type.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (type.equalsIgnoreCase("oval")) {
            return new Oval();
        } else if (type.equalsIgnoreCase("square")) {
            return new Square();
        } else {
            throw new RuntimeException();
        }

    }
}
