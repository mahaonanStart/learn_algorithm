package algorithm.test;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-29 22:41
 * @Description: 单例模式
 */
public class Test8 {

    private Test8(){

    }

    private static class B{
        public static Test8 test8 = new Test8();
    }

    public static Test8 a(){
        return B.test8;
    }

    public static void main(String[] args) {
        Test8 test8 = Test8.a();
        Test8 test1 = Test8.a();
        System.out.println(test1 == test8);

    }
}
