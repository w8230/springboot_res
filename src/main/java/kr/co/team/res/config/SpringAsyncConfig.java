package kr.co.team.res.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


/**
 * spring boot async 활성화. 2020.02.29
 * @author user
 *
 */
@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {
    
    @Override
    public Executor getAsyncExecutor() {
        //return new ThreadPoolTaskExecutor();

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		
		//max:10,capa:10 요청 30일경우 오류발생됨.주의.java.util.concurrent.RejectedExecutionException
		//[Running, pool size = 10, active threads = 10, queued tasks = 10, completed tasks = 0]
		//executor.setQueueCapacity(10);  //대기최대수 
		
		executor.initialize();
		
		return executor;
    }
     
}

