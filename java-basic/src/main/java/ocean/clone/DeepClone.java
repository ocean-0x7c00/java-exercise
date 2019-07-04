package ocean.clone;

import java.util.Date;

/**
 * 实现深克隆
 * <p>
 * 先对要复制的对象调用clone方法，然后在对对象中的非基本数据类型也调用
 * clone()方法完成复制
 *
 * @author yancy
 * @date 2019/6/17
 */
public class DeepClone implements Cloneable {
    private Date birth = new Date();

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void changeDate() {
        this.birth.setDate(4);
    }

    @Override
    protected DeepClone clone() throws CloneNotSupportedException {
        DeepClone deepClone = (DeepClone) super.clone();
        deepClone.birth = (Date) this.birth.clone();
        return deepClone;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        DeepClone a = new DeepClone();
        DeepClone b = (DeepClone) a.clone();
        b.changeDate();
        System.out.println("a= " + a.getBirth());
        System.out.println("b= " + b.getBirth());
    }
}
