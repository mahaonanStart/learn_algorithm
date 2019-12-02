package design_pattern.singleton.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-10-28 21:58
 * @Description: Runtime类就是一个经典的单例模式
 */
public class RuntimeTest {
    public static void main(String[] args) throws Exception{
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ls");
        System.out.println("Java虚拟机中的空闲内存量:"+runtime.freeMemory());
        System.out.println("Java 虚拟机试图使用的最大内存量:"+ runtime.maxMemory());
        System.out.println("返回 Java 虚拟机中的内存总量:"+ runtime.totalMemory());
        process.destroy();
    }
}
