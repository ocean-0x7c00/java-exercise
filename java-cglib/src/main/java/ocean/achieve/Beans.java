package ocean.achieve;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

/**
 * https://blog.csdn.net/it_freshman/article/details/81223524
 * https://www.throwable.club/2018/12/16/cglib-api/#InterfaceMaker
 *
 * @author yancy
 * @date 2019/8/8
 */
public class Beans {
    public static void main(String[] args) throws ClassNotFoundException {
        //1. 创建一个Bean生成器
        BeanGenerator generator = new BeanGenerator();

        //2. 往Bean中动态添加属性(属性名称+属性类型)
        generator.addProperty("age", (Class) Class.forName("java.lang.Integer"));

        //3. 创建Bean
        Object o = generator.create();

        //4. 给Bean创建一个属性值Map，BeanMap用来存放具体的属性值
        BeanMap beanMap = BeanMap.create(o);

        //5. 将具体的属性值添加到对象中
        beanMap.put("age", 13);

//        System.out.println(JSON.toJSONString(o));
    }
}
