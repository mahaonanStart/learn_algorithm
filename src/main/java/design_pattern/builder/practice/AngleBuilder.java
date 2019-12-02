package main.java.design_pattern.builder.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-02 16:39
 * @Description:
 */
public class AngleBuilder extends RoleBuilder{

    @Override
    public void buildSex() {
        role.setSex("女天使");
    }

    @Override
    public void buildFace() {
        role.setFace("漂亮的面容");
    }

    @Override
    public void buildCustom() {
        role.setCustom("身穿一席白裙");
    }

    @Override
    public void buildHairstyle() {
        role.setHairstyle("披肩的长发");
    }
}
