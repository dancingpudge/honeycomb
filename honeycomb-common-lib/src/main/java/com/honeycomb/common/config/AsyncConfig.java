package com.honeycomb.common.config;

import com.honeycomb.common.annotation.EnableHoneycombAsync;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池的配置
 *
 * @author Liuh
 */
@Configuration
@ConditionalOnBean(annotation = EnableHoneycombAsync.class)
public class AsyncConfig {
    private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int QUEUE_CAPACITY = 1000;

    private static final int KEEP_ALIVE_SECONDS = 200;

    private static final int AWAIT_TERMINATION_SECONDS = 60;

    private static final String THREAD_NAME_PREFIX = "honeycomb-thread-pool-";

    @Bean("asyncTaskExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        asyncTaskExecutor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        asyncTaskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        asyncTaskExecutor.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS);
        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        asyncTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }

}