package com.exemplo.quartzdocker;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myFirstJobDetail() {
        return JobBuilder.newJob(MyFirstJob.class)
                .withIdentity("myFirstJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger myFirstJobTrigger(JobDetail myFirstJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(myFirstJobDetail)
                .withIdentity("myFirstJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * * * ?"))
                .build();
    }

    @Bean
    public JobDetail mySecondJobDetail() {
        return JobBuilder.newJob(MySecondJob.class)
                .withIdentity("mySecondJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger mySecondJobTrigger(JobDetail mySecondJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(mySecondJobDetail)
                .withIdentity("mySecondJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * * * ?"))
                .build();
    }

    @Bean
    public JobDetail myThirdJobDetail() {
        return JobBuilder.newJob(MyThirdJob.class)
                .withIdentity("myThirdJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger myThirdJobTrigger(JobDetail myThirdJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(myThirdJobDetail)
                .withIdentity("myThirdJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */1 * * * ?"))
                .build();
    }

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean factory, Trigger myFirstJobTrigger, JobDetail myFirstJobDetail) {
        Scheduler scheduler = factory.getScheduler();
        try {
            JobKey jobKey = JobKey.jobKey(myFirstJobDetail.getKey().getName(), myFirstJobDetail.getKey().getGroup());
            if (!scheduler.checkExists(jobKey)) {
                scheduler.scheduleJob(myFirstJobDetail, myFirstJobTrigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheduler;
    }
}