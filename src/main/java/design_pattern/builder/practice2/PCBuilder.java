package design_pattern.builder.practice2;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-03 17:57
 * @Description: 台式机建造者
 */
public class PCBuilder extends ComputerBuidler{
    @Override
    public void buildCpu() {
        this.computer.setCpu("cpu为i7-9700k");
    }

    @Override
    public void buildMemory() {
        this.computer.setMemory("内存为32g");
    }

    @Override
    public void buildHardDisk() {
        this.computer.setComputerCase("硬盘为三星固态1T");
    }

    @Override
    public void buildMainBoard() {
        this.computer.setMainBoard("主板是技嘉Z390");
    }

    @Override
    public void buildDisplay() {
        this.computer.setDisplay("显示器32寸4k 144hz显示器");
    }

    @Override
    public void buildComputerCase() {
        this.computer.setComputerCase("机箱为微星至尊");
    }
}
