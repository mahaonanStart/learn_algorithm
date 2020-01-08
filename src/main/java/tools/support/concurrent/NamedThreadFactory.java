package tools.support.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-05 15:19
 * @Description: 自定义线程工厂
 */
public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    private String prefix;

    private final ThreadGroup group;

    private final AtomicInteger number;

    /**
     * 默认命名规则
     */
    public NamedThreadFactory() {
        this("pool-" + POOL_SEQ.getAndIncrement());
    }

    /**
     * 自定义前缀命名
     * @param prefix
     */
    public NamedThreadFactory(String prefix) {
        this.number = new AtomicInteger(1);
        this.prefix = prefix + "-thread-";
        SecurityManager s = System.getSecurityManager();
        this.group = s == null ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    /**
     * 创建符合命名规则的线程
     * @param r
     * @return
     */
    @Override
    public Thread newThread(Runnable r) {
        String name = this.prefix + this.number.getAndIncrement();
        Thread t = new Thread(this.group, r, name, 0L);
        t.setDaemon(false);
        return t;
    }
}
