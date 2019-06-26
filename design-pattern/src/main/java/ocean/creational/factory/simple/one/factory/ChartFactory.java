package ocean.creational.factory.simple.one.factory;

import ocean.creational.factory.simple.one.product.Chart;
import ocean.creational.factory.simple.one.product.HistogramChart;
import ocean.creational.factory.simple.one.product.LineChart;
import ocean.creational.factory.simple.one.product.PieChart;

public class ChartFactory {
    public static Chart createChart(String type) {
        Chart chart = null;
        if (type.equalsIgnoreCase("histogram")) {
            chart = new HistogramChart();
            System.out.println("初始化设置柱状图！");
        } else if (type.equalsIgnoreCase("pie")) {
            chart = new PieChart();
            System.out.println("初始化设置饼状图！");
        } else if (type.equalsIgnoreCase("line")) {
            chart = new LineChart();
            System.out.println("初始化设置折线图！");
        }
        return chart;
    }
}
