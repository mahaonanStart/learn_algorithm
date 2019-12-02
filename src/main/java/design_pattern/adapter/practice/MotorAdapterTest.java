package main.java.design_pattern.adapter.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 17:00
 * @Description:
 */
public class MotorAdapterTest {
    public static void main(String[] args) {

        OpticalMotor opticalMotor = new OpticalMotor();
        Motor motor = new OpticalAdapter(opticalMotor);
        motor.drive();
    }
}
