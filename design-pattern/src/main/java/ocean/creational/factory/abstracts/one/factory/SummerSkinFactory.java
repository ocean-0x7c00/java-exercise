package ocean.creational.factory.abstracts.one.factory;

import ocean.creational.factory.abstracts.origin.product.*;

/**
 * Summer皮肤工厂：具体工厂
 */
public class SummerSkinFactory implements SkinFactory {
    @Override
    public Button createButton() {
        return new SummerButton();
    }

    @Override
    public TextField createTextField() {
        return new SummerTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}
