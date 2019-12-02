package design_pattern.builder.practice2;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-03 17:54
 * @Description:
 */
public abstract class ComputerBuidler {
    //一种组合的关系，这个buidler就是用来建造这个产品的，因此不可分离
    protected Computer computer = new Computer();

    public abstract void buildCpu();

    public abstract void buildMemory();

    public abstract void buildHardDisk();

    public abstract void buildMainBoard();

    public abstract void buildDisplay();

    public abstract void buildComputerCase();

    /**
     * 钩子方法，判断某个组件是否需要建造
     * @return
     */
    public boolean isHaveDisplay() {
        return true;
    }

    public Computer getComputer() {
        return this.computer;
    }


}
