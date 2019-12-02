package main.java.design_pattern.adapter.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-11 16:58
 * @Description: 电能适配器
 */
public class ElectricAdapter implements Motor{

    private ElectricMotor electricMotor;

    public ElectricAdapter(ElectricMotor electricMotor) {
        this.electricMotor = electricMotor;
    }

    @Override
    public void drive() {
        electricMotor.electriDrive();
    }
}
