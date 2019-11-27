package design_pattern.builder.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-02 16:43
 * @Description: 指挥者
 */
public class RoleDirector {
    //聚合关系，可以分离
    private RoleBuilder roleBuilder;

    public RoleDirector(RoleBuilder roleBuilder) {
        this.roleBuilder = roleBuilder;
    }

    public Role createRole() {
        roleBuilder.buildSex();
        roleBuilder.buildFace();
        roleBuilder.buildCustom();
        roleBuilder.buildHairstyle();
        return roleBuilder.getRole();
    }
}
