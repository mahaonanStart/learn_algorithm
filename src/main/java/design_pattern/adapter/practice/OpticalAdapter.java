package main.java.design_pattern.adapter.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 17:00
 * @Description:
 */
public class OpticalAdapter implements Motor{
    private OpticalMotor opticalMotor;

    public OpticalAdapter(OpticalMotor opticalMotor) {
        this.opticalMotor = opticalMotor;
    }

    @Override
    public void drive() {
        opticalMotor.opticalDriver();
    }
}
