package ocean.behavioral.strategy.one;

/**
 * 策略模式
 * 实现一个功能，可以有多种算法。策略模式的目的是将算法的定义和使用分开，
 * 也就是将算法的行为和环境分开
 * <p>
 * 具体方法
 * 定义具体的类来封装不同的算法，每个封装算法的类称为策略，为了保证策略的
 * 一致性，提供一个抽象策略类来做规则的定义，每种算法对应一个具体的策略类
 *
 * @author ocean
 */
public class Client {
    public static void main(String[] args) {
        Context s = new Context(new ConcreteStrategyA());
        s.m();
    }
}
