package ocean.behavioral.strategy.origin;

import lombok.Setter;

/**
 * 电影票类
 *
 * @author ocean
 */
@Setter
public class MovieTicket {
    /**
     * 电影票价格
     */
    private double price;

    /**
     * 电影票类型
     */
    private String type;

    public double getPrice() {
        return calculate();
    }

    /**
     * 计算打折之后的票价
     *
     * @return
     */
    public double calculate() {
        if (this.type.equalsIgnoreCase("student")) {
            System.out.println("学生票：");
            return this.price * 0.8;
        } else if (this.type.equalsIgnoreCase("children") && this.price >= 20) {
            System.out.println("儿童票：");
            return this.price - 10;
        } else if (this.type.equalsIgnoreCase("vip")) {
            System.out.println("VIP票：");
            return this.price * 0.5;
        } else {
            return this.price;
        }
    }
}
