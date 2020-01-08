package tools.support.timing;

import tools.constant.ScheduleType;
import java.util.Date;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-06 15:23
 * @Description: 定时器接口
 */
public interface ScheduleTaskService {

    /**
     * 开启定时任务
     * @param scheduleType 定时任务类型
     * @param task 定时任务
     * @param key 唯一标识符
     * @param date 定时执行日期
     */
    void scheduleTask(ScheduleType scheduleType, IScheduleTask task, String key, Date date);

    /**
     * 取消定时任务
     * @param scheduleType 定时任务类型
     * @param key 唯一标识符
     */
    void cancelTask(ScheduleType scheduleType, String key);
}
