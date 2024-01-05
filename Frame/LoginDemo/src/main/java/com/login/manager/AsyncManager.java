package com.login.manager;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.login.utils.SpringUtils;
import com.login.utils.Threads;
import org.springframework.boot.autoconfigure.web.ServerProperties;


/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-21 15:48
 * @description: 异步任务管理器
 */
public class AsyncManager {
    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    /**
     * 单例模式
     */
    private AsyncManager() {

    }

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me(){
        return me;
    }

    /**
     * 执行任务
     */
    public void execute(TimerTask task){
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MICROSECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(executor);
    }
}

