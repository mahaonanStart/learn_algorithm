package design_pattern.builder.practice2;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-03 18:01
 * @Description: 笔记本建造者
 */
public class LaptopBuilder extends ComputerBuidler{
    @Override
    public void buildCpu() {
        this.computer.setCpu("i5-9700");
    }

    @Override
    public void buildMemory() {

    }

    @Override
    public void buildHardDisk() {

    }

    @Override
    public void buildMainBoard() {

    }

    @Override
    public void buildDisplay() {

    }

    @Override
    public void buildComputerCase() {

    }
}
