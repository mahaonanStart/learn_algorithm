package tools.support.timing;

import tools.constant.ScheduleType;

/**
 * @Author: M˚Haonan
 * @Date: 2019-11-06 15:41
 * @Description: 任务执行接口
 */
public interface IScheduleTask {

    /**
     * 执行定时任务
     * @param type 定时任务类型
     * @param key 唯一标识符
     * @return
     */
    Object run(ScheduleType type, String key);
}
