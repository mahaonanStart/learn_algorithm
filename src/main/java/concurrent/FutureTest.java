package concurrent;

import java.util.concurrent.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-06 11:43
 * @Description: Future 接口测试
 */
public class FutureTest {

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Future可以拿到callable接口run方法的返回值
     * @throws Exception
     */
    public static void getFutureTest() throws Exception {
        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(2000);
            return 123;
        });
        while (!future.isDone()) {
            System.out.println("执行中");
            Thread.sleep(300);
        }
        System.out.println(future.get(5, TimeUnit.MILLISECONDS));
    }

    /**
     * 取消任务的测试
     * @throws Exception
     */
    public static void cancelFutureTest() throws Exception {
        Future<String> future = executor.submit(() -> {
            while (true) {
                System.out.println("thread is running...");
                Thread.sleep(1000);
            }
        });

        Thread.sleep(3000);
        future.cancel(true);
        System.out.println("thread is end");
        executor.shutdown();
    }

    /**
     * 定时执行测试
     * @throws Exception
     */
    public static void scheduleTaskTest() throws Exception {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> schedule = scheduledThreadPool.schedule(() -> {
            System.out.println("定时时间到");
        }, 3, TimeUnit.SECONDS);
    }


    public static void main(String[] args) throws Exception{
//        getFutureTest();
//        cancelFutureTest();
        scheduleTaskTest();
    }
}
