package ocean.factory.simple.one.product;

public class LineChart implements Chart{
    public LineChart() {
        System.out.println("创建饼状图");
    }

    @Override
    public void display() {
        System.out.println("显示饼状图");
    }
}
