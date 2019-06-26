package ocean.factory.abstracts.one.factory;

import ocean.factory.abstracts.origin.product.Button;
import ocean.factory.abstracts.origin.product.ComboBox;
import ocean.factory.abstracts.origin.product.TextField;

//界面皮肤工厂接口：抽象工厂
public interface SkinFactory {
    Button createButton();

    TextField createTextField();

    ComboBox createComboBox();
}
