package ocean.behavioral.strategy.two;

import lombok.Setter;

/**
 * Context
 * 电影票类：环境类
 *
 * @author ocean
 */
@Setter
public class MovieTicket {
    private double price;

    /**
     * 维持一个对抽象折扣类的引用
     */
    private Discount discount;

    public double getPrice() {
        return discount.calcualte(price);
    }
}
