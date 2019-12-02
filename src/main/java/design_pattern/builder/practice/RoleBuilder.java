package design_pattern.builder.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-02 16:36
 * @Description:
 */
public abstract class RoleBuilder {

    //组合关系，RoleBuilder与Role不可分离
    protected Role role = new Role();

    public abstract void buildSex();
    public abstract void buildFace();
    public abstract void buildCustom();
    public abstract void buildHairstyle();

    public Role getRole() {
        return role;
    }
}
