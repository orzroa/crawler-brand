package com.hizhu.crawler.brand.quartz;

import com.hizhu.crawler.brand.entity.enums.City4BeiKe;
import com.hizhu.crawler.brand.entity.enums.MagicNum;
import com.hizhu.crawler.brand.service.BrandInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/10 15:01
 */
@Component
@Slf4j
public class BeikeSchedule {

    @Autowired
    private BrandInfoService brandInfoService;

    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4SH(){
        this.scheduler(City4BeiKe.SHANGHAI.getCityShortName());
    }

    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4BJ(){
        this.scheduler(City4BeiKe.BEIJING.getCityShortName());
    }
    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4TJ(){
        this.scheduler(City4BeiKe.TIANJIN.getCityShortName());
    }
    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4NJ(){
        this.scheduler(City4BeiKe.NANJING.getCityShortName());
    }
    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4GZ(){
        this.scheduler(City4BeiKe.GUANGZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4ZZ(){
        this.scheduler(City4BeiKe.ZHENGZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4HZ(){
        this.scheduler(City4BeiKe.HANGZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4SU(){
        this.scheduler(City4BeiKe.SUZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4SZ(){
        this.scheduler(City4BeiKe.SHENZHENG.getCityShortName());
    }
    @Scheduled(cron = "0 27 18 ? * *")
    public void scheduler4WH(){
        this.scheduler(City4BeiKe.WUHAN.getCityShortName());
    }



    private void scheduler(String shortName){
        log.info(shortName.toString());
        //上海
        for (int i = 0; i < MagicNum.N_6; i ++){
            BeiKeCrawlerThread beikeBrandThread = new BeiKeCrawlerThread();
            HashMap<String,Object> param = new HashMap<>(32);
            param.put("brandInfoService",brandInfoService);
            param.put("threadName",  "Thread：" + shortName);
            param.put("cityShortName", shortName);
            beikeBrandThread.setMap(param);
            new Thread(beikeBrandThread,shortName).start();
        }

    }

}
