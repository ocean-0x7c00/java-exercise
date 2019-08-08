package ocean;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 根据已有的映射关系(配置文件中的Key-Value)
 * 1.生成属性 访问权限、数据类型、属性名称
 * 2.生成方法 访问权限、返回类型、参数列表、方法体、返回值
 *
 * @author yancy
 * @date 2019/8/8
 */
public class DynamicBean {
    /**
     * 动态生成的类
     */
    private Object object = null;

    /**
     * 存放属性名称以及属性的类型
     */
    private BeanMap beanMap = null;

    private Object generateBean(Map propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Iterator i = keySet.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            generator.addProperty(key, (Class) propertyMap.get(key));
        }

        return generator.create();
    }

    public DynamicBean() {
        super();
    }

    public DynamicBean(Map propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    public Object getObject() {
        return object;
    }

    public Object getValue(String property) {
        return beanMap.get(property);
    }

    public void setValue(Object property, Object value) {
        this.beanMap.put(property, value);
    }
}
