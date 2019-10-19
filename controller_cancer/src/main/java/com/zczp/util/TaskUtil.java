package com.zczp.util;

import com.zczp.service_cancer.Impl.TbCollectServiceImpl;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.service_cancer.Impl.TbReliabilityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 可信度的定时任务
 */
@Slf4j
public class TaskUtil extends QuartzJobBean {

    @Autowired
    TbReliabilityServiceImpl tbReliabilityService;
    @Autowired
    TbPostServiceImpl tbPostService;
    @Autowired
    TbCollectServiceImpl tbCollectService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("同步数据到数据库-------- {}", sdf.format(new Date()));

        //将 Redis 里的可信度信息同步到数据库里
        tbReliabilityService.transReliabilityToDB();
        tbPostService.transReliabilityCountToDB();
        //将redis里的收藏信息同步到数据库
        tbCollectService.transCollectToDB();
        //将redis中post浏览数同步到数据库
        tbPostService.transCountToDB();
    }
}

