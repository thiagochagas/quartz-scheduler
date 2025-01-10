package com.exemplo.quartzdocker;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MySecondJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(MySecondJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("############ JOB2. Executando em um dos pods...");
        // Lógica do job vai aqui
    }
}