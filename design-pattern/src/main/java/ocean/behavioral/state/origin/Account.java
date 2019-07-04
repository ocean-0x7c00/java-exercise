package ocean.behavioral.state.origin;

/**
 * 状态模式
 * <p>
 * 事例：
 * 行账户(Account)是该系统的核心类之一，通过分析，发现在该系统中，账户存在三种状态，且在不同状态下账户存在不同的行为，
 * (1)如果账户中余额大于等于0，则账户的状态为正常状态(Normal State)，此时用户既可以向该账户存款也可以从该账户取款；
 * (2) 如果账户中余额小于0，并且大于-2000，则账户的状态为透支状态(Overdraft State)，此时用户既可以向该账户存款也可以从该账户取款，
 * 但需要按天计算利息；
 * (3) 如果账户中余额等于-2000，那么账户的状态为受限状态(Restricted State)，此时用户只能向该账户存款，不能再从中取款，同时也将按天计算利息；
 * (4) 根据余额的不同，以上三种状态可发生相互转换。
 *
 *
 * 存在问题：
 * 1.几乎每个方法都包含状态检测，以判断在该状态下是否具有该方法以及在特定状态下该方法如何实现，
 * 导致代码非常冗长，可维护性较差；
 * 2.stateCheck方法过于复杂，还有大量的if-else，代码测试难度大，不利于维护
 * 3.系统的扩展性差
 *
 * 解决方法：
 * 使用状态模式
 */
public class Account {
    /**
     * 状态
     */
    private String state;

    /**
     * 余额
     */
    private int balance;


    /**
     * 存款操作
     */
    public void deposit() {
        //存款
        stateCheck();
    }


    /**
     * 取款操作
     */
    public void withdraw() {
        if (state.equalsIgnoreCase("NormalState") || state.equalsIgnoreCase("OverdraftState ")) {
            //取款
            stateCheck();
        } else {
            //取款受限
        }
    }

    /**
     * 计算利息操作
     */
    public void computeInterest() {
        if (state.equalsIgnoreCase("OverdraftState") || state.equalsIgnoreCase("RestrictedState ")) {
            System.out.println("计算利息");
        }
    }

    public void stateCheck() {
        if (balance >= 0) {
            state = "NormalState";
        } else if (balance > -2000 && balance < 0) {
            state = "OverdraftState";
        } else if (balance == -2000) {
            state = "RestrictedState";
        } else if (balance < -2000) {
            System.out.println("操作受限");
        }
    }
}
