package design_pattern.builder.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-02 16:41
 * @Description:
 */
public class GhostBuilder extends RoleBuilder{

    @Override
    public void buildSex() {
        role.setSex("男幽灵");
    }

    @Override
    public void buildFace() {
        role.setFace("极其丑陋");
    }

    @Override
    public void buildCustom() {
        role.setCustom("穿一件刺眼的黑衣");
    }

    @Override
    public void buildHairstyle() {
        role.setHairstyle("留着光头");
    }
}
