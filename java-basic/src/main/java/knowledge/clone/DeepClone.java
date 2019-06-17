package knowledge.clone;

import java.util.Date;

/**
 * 实现深克隆
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
