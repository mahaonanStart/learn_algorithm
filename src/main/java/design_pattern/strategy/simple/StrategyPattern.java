package main.java.design_pattern.strategy.simple;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-16 12:13
 * @Description:
 */

// abstract strategy class
interface Strategy {
    void strategyMethod();
}

class ConcreteStrategyA implements Strategy {

    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问");
    }
}

class ConcreteStrategyB implements Strategy {

    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被访问");
    }
}

//环境类
class Context {
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod() {
        strategy.strategyMethod();
    }
}



public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context();
        Strategy s = new ConcreteStrategyA();
        context.setStrategy(s);
        context.strategyMethod();
        System.out.println("----------");
        s = new ConcreteStrategyB();
        context.setStrategy(s);
        context.strategyMethod();
    }

}
