package ocean.factory.simple.one.product;

public class PieChart implements Chart {
    public PieChart() {
        System.out.println("创建折线图");
    }

    @Override
    public void display() {
        System.out.println("显示折线图");
    }
}
