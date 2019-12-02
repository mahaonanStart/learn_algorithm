package main.java.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-05 16:20
 * @Description: ThreadGroup类的测试
 */

public class ThreadGroupTest {

    /**
     * 线程组创建的测试方法
     */
    public static void createTest() {
        //获取当前线程的group
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        //在当前线程中新建一个线程组group1
        ThreadGroup group1 = new ThreadGroup("group1");
        //true，即group1的父线程组就是main线程所在的线程组
        System.out.println(group1.getParent() == currentGroup);
        //在group1下新建一个线程组group2
        ThreadGroup group2 = new ThreadGroup(group1, "group2");
        System.out.println(group2.getParent() == group1);
    }


    /**
     * 线程组的基本操作测试
     */
    public static void basicOperationTest() throws Exception{
        //获取当前线程的group
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        //在当前线程中新建一个线程组group1
        ThreadGroup group1 = new ThreadGroup("group1");
        //true，即group1的父线程组就是main线程所在的线程组
        System.out.println(group1.getParent() == currentGroup);
        //在group1下新建一个线程组group2
        ThreadGroup group2 = new ThreadGroup(group1, "group2");
        System.out.println(group2.getParent() == group1);
        //向线程组group1中添加一个线程thread1
        Thread thread1 = new Thread(group1, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("10s过去了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1");
        //设置线程为守护线程
        thread1.setDaemon(true);
        thread1.start();
        TimeUnit.MILLISECONDS.sleep(1);
        //递归获取当前线程组中活跃线程的估计值 3
        System.out.println("activeCount = " + currentGroup.activeCount());
        //递归获取当前线程组中的活跃的group 2
        System.out.println("activeGroupCount = " + currentGroup.activeGroupCount());
        //获取group的优先级，默认为10
        System.out.println("maxPriority = " + currentGroup.getMaxPriority());
        //获取线程组的名称  main
        System.out.println("name = " + currentGroup.getName());
        //获取线程组的父线程组，如果不存在返回null    java.lang.ThreadGroup[name=system,maxpri=10]
        System.out.println("parent = " + currentGroup.getParent());
        //打印所有线程组和线程信息
        currentGroup.list();
        System.out.println("--------------------------------");
        //判断当前group是不是给定group的父线程(爷爷也算)，如果两者一样，也会返回true
        System.out.println("parentOf = " + currentGroup.parentOf(group1));
        System.out.println("parentOf = " + currentGroup.parentOf(currentGroup));
    }

    /**
     * 线程组interrupt方法测试
     * 将会终止所有线程组中的线程
     */
    public static void interruptTest() throws Exception{
        ThreadGroup group = new ThreadGroup("group1");
        new Thread(group, () -> {
            while(true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
            System.out.println("thread1将会退出");
        }, "thread1").start();
        new Thread(group, () -> {
            while(true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
            System.out.println("thread2将会退出");
        }, "thread2").start();
        TimeUnit.MILLISECONDS.sleep(2);
        group.interrupt();
    }

    /**
     * destroy方法测试, 仅仅只会摧毁当前线程组以及子线程组，不会影响其他的线程组
     */
    public static void destroyTest() {
        ThreadGroup group = new ThreadGroup("group1");
        ThreadGroup group2 = new ThreadGroup(group, "group2");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        //摧毁前
        System.out.println("group.isDestroyed = " + group.isDestroyed());
        mainGroup.list();
        group.destroy();
        //摧毁后
        System.out.println("group.isDestroyed = " + group.isDestroyed());
        mainGroup.list();
    }

    /**
     * 线程组守护线程组测试方法
     * 并不会影响其线程是否为守护线程，仅仅表示当他内部没有活跃的线程时，会自动销毁
     */
    public static void daemonTest() throws Exception{
        ThreadGroup group1 = new ThreadGroup("group1");
        new Thread(group1, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group1-thread1").start();
        ThreadGroup group2 = new ThreadGroup("group2");
        new Thread(group2, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group1-thread2").start();
        group2.setDaemon(true);
        TimeUnit.SECONDS.sleep(3);
        //false, 即使内部没有活跃的线程，也不会销毁
        System.out.println(group1.isDestroyed());
        //true，group2设置为daemon, 内部没有活跃线程时，会自动销毁
        System.out.println(group2.isDestroyed());
    }


    public static void main(String[] args) throws Exception{
//        interruptTest();
//        destroyTest();
        daemonTest();

    }



}
