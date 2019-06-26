package ocean.creational.factory.abstracts.origin;

import ocean.creational.factory.abstracts.origin.factory.*;
import ocean.creational.factory.abstracts.origin.product.Button;
import ocean.creational.factory.abstracts.origin.product.ComboBox;
import ocean.creational.factory.abstracts.origin.product.TextField;

/**
 * 实例：界面皮肤库的初始设计
 * 用户在使用时可以通过菜单来选择皮肤，
 * 不同的皮肤将提供视觉效果不同的按钮、文本框、组合框等界面元素
 * <p>
 * 先使用工厂方法模式，总结优缺点，再使用抽象工厂模式
 * <p>
 * 工厂模式的缺陷
 * 1.当需要增加新的皮肤时，虽然不要修改现有代码，但是需要增加大量类，
 * 针对每一个新增具体组件都需要增加一个具体工厂，类的个数成对增加，
 * 这无疑会导致系统越来越庞大，增加系统的维护成本和运行开销
 * <p>
 * 2.由于同一种风格的具体界面组件通常要一起显示，
 * 因此需要为每个组件都选择一个具体工厂，用户在使用时必须逐个进行设置，
 * 如果某个具体工厂选择失误将会导致界面显示混乱，
 * 虽然我们可以适当增加一些约束语句，但客户端代码和配置文件都较为复杂
 *
 * @author yancy
 * @date 2019/6/25
 */
public class Client {
    public static void main(String[] args) {
        /**
         * 如果需要切换风格，需要修改下面代码
         * 需要修改new SpringButtonFactory()
         * 需要修改new SpringTextFiledFactory()
         * 需要修改new SpringComboBoxFactory()
         *
         * */
        ButtonFactory buttonFactory = new SpringButtonFactory();
        Button button = buttonFactory.createButton();

        TextFieldFactory textFieldFactory = new SpringTextFiledFactory();
        TextField textField = textFieldFactory.createTextField();

        ComboBoxFactory comboBoxFactory = new SpringComboBoxFactory();
        ComboBox comboBox = comboBoxFactory.createComboBox();



    }
}
