package design_pattern.builder.practice2;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-03 17:51
 * @Description: 电脑产品类
 */
public class Computer {

    private String cpu;
    private String memory;
    private String hardDisk;
    private String display;
    private String computerCase;
    private String mainBoard;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getComputerCase() {
        return computerCase;
    }

    public void setComputerCase(String computerCase) {
        this.computerCase = computerCase;
    }

    public String getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(String mainBoard) {
        this.mainBoard = mainBoard;
    }
}
