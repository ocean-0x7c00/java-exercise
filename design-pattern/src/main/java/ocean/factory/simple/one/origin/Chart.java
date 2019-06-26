package ocean.factory.simple.one.origin;

/**
 * Sunny软件公司欲基于Java语言开发一套图表库，该图表库可以为应用系统提供各种不同外观的图表，
 * 例如柱状图、饼状图、折线图等。
 * Sunny软件公司图表库设计人员希望为应用系统开发人员提供一套灵活易用的图表库，
 * 而且可以较为方便地对图表库进行扩展，以便能够在将来增加一些新类型的图表。
 * <p>
 * 在该类的设计中存在如下几个问题：
 * <p>
 * 1.包含大量if....else...，代码冗长，难维护、难测试、难阅读
 * 2.Chart类职责过重，负责初始化和显示所有图表对象，违反了单一职责原则
 * 3.当需要增加新类型的图表时，必须修改Chart类的源代码，违反了“开闭原则”。
 * 4.客户端只能通过new关键字来直接创建Chart对象，Chart类与客户端类耦合度较高，
 * 对象的创建和使用无法分离
 * 5.在创建Chart对象前可能还要对图表进行初始化设置，若构造方法中没有提供默认的初始化设置，
 * 则容易导致代码重复
 * <p>
 * 面对一个如此巨大、职责如此重，且与客户端代码耦合度非常高的类，我们应该怎么办？本章将要介绍的简单工厂模式将在一定程度上解决上述问题。
 *
 * @author ocean
 */
public class Chart {
    /**
     * 图表类型
     */
    private String type;

    public Chart(Object[][] data, String type) {
        this.type = type;
        if (type.equalsIgnoreCase("histogram")) {
            System.out.println("初始化柱状图");
        } else if (type.equalsIgnoreCase("pie")) {
            System.out.println("初始化饼状图");
        } else if (type.equalsIgnoreCase("line")) {
            System.out.println("初始化折线图");
        }
    }

    public void display() {
        if (this.type.equalsIgnoreCase("histogram")) {
            System.out.println("显示柱状图");
        } else if (this.type.equalsIgnoreCase("pie")) {
            System.out.println("显示饼状图");
        } else if (this.type.equalsIgnoreCase("line")) {
            System.out.println("显示折线图");
        }
    }
}
