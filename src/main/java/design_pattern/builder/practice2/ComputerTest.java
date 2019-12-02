package design_pattern.builder.practice2;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-03 18:04
 * @Description:
 */
public class ComputerTest {

    public static void main(String[] args) {
        ComputerBuidler computerBuidler = new PCBuilder();
        ComputerDirector director = new ComputerDirector(computerBuidler);
        Computer computer = director.construct();
        System.out.println(computer.getCpu());
    }
}
