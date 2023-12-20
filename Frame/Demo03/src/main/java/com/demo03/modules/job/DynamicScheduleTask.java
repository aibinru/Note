package com.demo03.modules.job;

import com.demo03.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

@Configuration
@EnableAsync    //开启多线程
public class DynamicScheduleTask /*implements SchedulingConfigurer*/ {

    @Mapper
    public interface CronMapper {
        @Select("select cron from cron limit 1")
        public String getCron();
    }

    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;

//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(
//                //1.添加定时任务内容
//                () -> System.out.println("执行动态定时任务："+ LocalDateTime.now().toLocalTime()),
//                //2.设置执行周期（Trigger）
//                triggerContext -> {
//                    //2.1 从数据库获取执行周期
//                    String cron = cronMapper.getCron();
//                    //2.1 合法性校验
//                    if(StringUtils.isEmpty(cron)){
//
//                    }
//                    //2.3 返回执行周期（Date）
//                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
//                }
//        );
//    }


}
