package tools.support.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-05 18:23
 * @Description: 线程池创建工具类
 */
public class ExecutorServiceUtil {

    /**
     * 创建带有延迟特性的线程池
     * @param name 线程名字的前缀
     * @param coreSize 核心线程数
     * @return
     */
    public static ScheduledThreadPoolExecutor newScheduledThreadPool(String name, int coreSize) {
        return new ScheduledThreadPoolExecutor(coreSize, new NamedThreadFactory(name));
    }
}
