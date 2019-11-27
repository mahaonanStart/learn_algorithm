package design_pattern.builder.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-02 16:45
 * @Description: 测试类
 */
public class RoleTest {
    public static void main(String[] args) {
        RoleBuilder builder = new GhostBuilder();
        RoleDirector director = new RoleDirector(builder);
        Role role = director.createRole();
        System.out.println(role.getSex() + role.getFace() + role.getCustom() + role.getHairstyle());
    }
}

