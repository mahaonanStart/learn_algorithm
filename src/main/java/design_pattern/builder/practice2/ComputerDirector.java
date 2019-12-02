package main.java.design_pattern.builder.practice2;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-03 18:02
 * @Description:
 */
public class ComputerDirector {

    private ComputerBuidler computerBuidler;

    public ComputerDirector(ComputerBuidler computerBuidler) {
        this.computerBuidler = computerBuidler;
    }

    public Computer construct() {
        computerBuidler.buildCpu();
        computerBuidler.buildHardDisk();
        if (computerBuidler.isHaveDisplay()) {
            computerBuidler.buildDisplay();
        }
        computerBuidler.buildMainBoard();
        computerBuidler.isHaveDisplay();
        computerBuidler.buildMemory();
        computerBuidler.buildComputerCase();
        return computerBuidler.getComputer();
    }
}
