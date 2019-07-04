package ocean.behavioral.strategy.one;

public class Context {
    AbstractStrategy strategy;

    public Context(AbstractStrategy strategy) {
        this.strategy = strategy;
    }

    public void m() {
        strategy.algorithm();
    }
}
