package tools.support.timing.impl;

import tools.constant.ScheduleType;
import tools.support.concurrent.ExecutorServiceUtil;
import tools.support.timing.IScheduleTask;
import tools.support.timing.ScheduleTaskService;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-06 15:30
 * @Description:
 */
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    private ConcurrentHashMap<String, ScheduledFuture<?>> futureMap = new ConcurrentHashMap<>();

    private ScheduledThreadPoolExecutor executor;

    public ScheduleTaskServiceImpl() {
        executor = ExecutorServiceUtil.newScheduledThreadPool("ScheduledTask", 1);
    }


    @Override
    public void scheduleTask(ScheduleType scheduleType, IScheduleTask task, String key, Date date) {
        String keyName = scheduleType + "-" + key;
        if (futureMap.containsKey(keyName)) {
            cancelTask(scheduleType, key);
        }
        long delaySeconds = seconds(date, new Date());
        if(delaySeconds < 0) {
            delaySeconds = 0;
        }
        ScheduledFuture<?> schedule = executor.schedule(() -> {
            task.run(scheduleType, key);
            futureMap.remove(keyName);
        }, delaySeconds, TimeUnit.SECONDS);
        futureMap.put(keyName, schedule);
    }

    @Override
    public void cancelTask(ScheduleType scheduleType, String key) {
        String keyName = scheduleType + "-" + key;
        if (futureMap.containsKey(keyName)) {
            futureMap.get(keyName).cancel(true);
            futureMap.remove(key);
        }
    }

    private long seconds(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / 1000;
    }}
