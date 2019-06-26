package ocean.creational.factory.abstracts.one.factory;

import ocean.creational.factory.abstracts.origin.product.Button;
import ocean.creational.factory.abstracts.origin.product.ComboBox;
import ocean.creational.factory.abstracts.origin.product.TextField;

//界面皮肤工厂接口：抽象工厂
public interface SkinFactory {
    Button createButton();

    TextField createTextField();

    ComboBox createComboBox();
}
