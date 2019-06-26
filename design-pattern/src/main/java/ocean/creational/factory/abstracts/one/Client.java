package ocean.creational.factory.abstracts.one;

import ocean.creational.factory.abstracts.one.factory.*;

/**
 * 实例：界面皮肤库的初始设计
 * 用户在使用时可以通过菜单来选择皮肤，
 * 不同的皮肤将提供视觉效果不同的按钮、文本框、组合框等界面元素
 * <p>
 * 先使用工厂方法模式，总结优缺点，再使用抽象工厂模式
 * <p>
 * 抽象工厂模式的缺陷
 * 在抽象工厂模式中，增加新的产品族很方便，但是增加新的产品等级结构很麻烦，抽象工厂模式的这种性质称为“开闭原则”的倾斜性。“开闭原则”要求系统对扩展开放，对修改封闭，通过扩展达到增强其功能的目的，对于涉及到多个产品族与多个产品等级结构的系统，其功能增强包括两方面：
 * <p>
 * (1) 增加产品族：对于增加新的产品族，抽象工厂模式很好地支持了“开闭原则”，
 * 只需要增加具体产品并对应增加一个新的具体工厂，对已有代码无须做任何修改。
 * <p>
 * (2) 增加新的产品等级结构：对于增加新的产品等级结构，需要修改所有的工厂角色，
 * 包括抽象工厂类，在所有的工厂类中都需要增加生产新产品的方法，违背了“开闭原则”。
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
//        ButtonFactory buttonFactory = new SpringButtonFactory();
//        Button button = buttonFactory.createButton();
//
//        TextFieldFactory textFieldFactory = new SpringTextFiledFactory();
//        TextField textField = textFieldFactory.createTextField();
//
//        ComboBoxFactory comboBoxFactory = new SpringComboBoxFactory();
//        ComboBox comboBox = comboBoxFactory.createComboBox();


        /**
         * 此时切换风格只需要修改一处代码 new SpringSkinFactory()
         * */
        SkinFactory factory = new SpringSkinFactory();
        ocean.creational.factory.abstracts.origin.product.Button button = factory.createButton();
        ocean.creational.factory.abstracts.origin.product.TextField textField = factory.createTextField();
        ocean.creational.factory.abstracts.origin.product.ComboBox comboBox = factory.createComboBox();
    }
}
