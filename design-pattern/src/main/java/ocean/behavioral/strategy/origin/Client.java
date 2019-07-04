package ocean.behavioral.strategy.origin;

/**
 * 策略模式
 * <p>
 * 实现某一个功能有多条途径，每一条途径对应一种算法，
 * 此时我们可以使用一种设计模式来实现灵活地选择解决途径，
 * 也能够方便地增加新的解决途径
 * <p>
 * 开发了一套影院售票系统，在该系统中需要为不同类型的用户提供不同的电影票打折方式，具体打折方案如下：
 * <p>
 * (1) 学生凭学生证可享受票价8折优惠；
 * (2) 年龄在10周岁及以下的儿童可享受每张票减免10元的优惠（原始票价需大于等于20元）；
 * (3) 影院VIP用户除享受票价半价优惠外还可进行积分，积分累计到一定额度可换取电影院赠送的奖品。
 * <p>
 * 该系统在将来可能还要根据需要引入新的打折方式
 *
 * 存在的问题：
 * 1.有新的打折方法是要修改MovieTicket的calculate，不满足开闭原则
 * 2.方法calculate中含有大量的if-else，不利于维护和测试
 * 3.MovieTicket类职责过重
 * 4.算法的复用性差，如果在另一个系统（如商场销售管理系统）中需要重用某些打折算法，
 * 只能通过对源代码进行复制粘贴来重用，无法单独重用其中的某个或某些算法（重用较为麻烦
 *
 * 产生此问题的原因
 * MovieTicket类职责过重，各种打折算法都定义在一个类中，这既不便于算法的重用，也不便于算法的扩展
 *
 * 解决方式
 * 使用策略模式：将算法的定义和使用分离，
 *
 */
public class Client {
    public static void main(String[] args) {
        MovieTicket mt = new MovieTicket();
        //原始票价
        double originalPrice = 60.0;
        double currentPrice; //折后价

        mt.setPrice(originalPrice);
        System.out.println("原始价为：" + originalPrice);
        System.out.println("---------------------------------");

        //学生票
        mt.setType("student");
        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
        System.out.println("---------------------------------");

        //儿童票
        mt.setType("children");
        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
    }
}
