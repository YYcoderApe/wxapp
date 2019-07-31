package com.zczp.config;

import com.zczp.util.ReliabilityTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    private static final String RELIABILITY_TASK_IDENTITY = "ReliabilityTaskQuartz";

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(ReliabilityTask.class).withIdentity(RELIABILITY_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(30)  //设置时间周期单位秒
                .withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(RELIABILITY_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

