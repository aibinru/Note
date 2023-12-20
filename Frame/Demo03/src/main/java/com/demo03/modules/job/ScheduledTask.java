package com.demo03.modules.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 定时任务
 */
@Component
public class ScheduledTask {

    /**
     *
     * @EnableScheduling @Scheduled
     * 在上面的入门例子中，使用了@Scheduled(fixedRate = 3000) 注解来定义每过 3 秒执行的任务，对于 @Scheduled 的使用可以总结如下几种方式：
     *
     * @Scheduled(fixedRate = 3000) ：上一次开始执行时间点之后 3 秒再执行（fixedRate 属性：定时任务开始后再次执行定时任务的延时（需等待上次定时任务完成），单位毫秒）
     * @Scheduled(fixedDelay = 3000) ：上一次执行完毕时间点之后 3 秒再执行（fixedDelay 属性：定时任务执行完成后再次执行定时任务的延时（需等待上次定时任务完成），单位毫秒）
     * @Scheduled(initialDelay = 1000, fixedRate = 3000) ：第一次延迟1秒后执行，之后按fixedRate的规则每 3 秒执行一次（initialDelay 属性：第一次执行定时任务的延迟时间，需配合fixedDelay或者fixedRate来使用）
     * @Scheduled(cron="0 0 2 1 * ? *") ：通过cron表达式定义规则
     *
     * 其中，常用的cron表达式有：
     *
     * 0 0 2 1 * ? * ：表示在每月 1 日的凌晨 2 点执行
     * 0 15 10 ? * MON-FRI ：表示周一到周五每天上午 10:15 执行
     * 0 15 10 ? 6L 2019-2020 ：表示 2019-2020 年的每个月的最后一个星期五上午 10:15 执行
     * 0 0 10,14,16 * * ? ：每天上午 10 点，下午 2 点，4 点执行
     * 0 0/30 9-17 * * ? ：朝九晚五工作时间内每半小时执行
     * 0 0 12 ? * WED ：表示每个星期三中午 12 点执行
     * 0 0 12 * * ? ：每天中午 12点执行
     * 0 15 10 ? * * ：每天上午 10:15 执行
     * 0 15 10 * * ? ：每天上午 10:15 执行
     * 0 15 10 * * ? * ：每天上午 10:15 执行
     * 0 15 10 * * ? 2019 ：2019 年的每天上午 10:15 执行
     */
    //@Scheduled(fixedDelay = 3000)//3秒执行一次
    public void scheduleTask() {
        System.out.println("任务执行时间："+ LocalDateTime.now());
    }
}
