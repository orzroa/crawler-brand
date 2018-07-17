package com.hizhu.crawler.brand.quartz;

import com.hizhu.crawler.brand.entity.enums.City4Mogu;
import com.hizhu.crawler.brand.quartz.mogo.*;
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
 * 2018/7/13 12:35
 */
@Component
@Slf4j
public class MogoSchedule {
    @Autowired
    private BrandInfoService brandInfoService;

    @Scheduled(cron = "0 0 0 ? * *")
    public void scheduler4SH(){
        this.schedulerSH(City4Mogu.SHANGHAI.getCityShortName());
    }
    @Scheduled(cron = "0 0 23 ? * *")
    public void scheduler4SZ(){
        this.schedulerSZ(City4Mogu.SHENZHENG.getCityShortName());
    }
    @Scheduled(cron = "0 0 23 ? * *")
    public void scheduler4SU(){
        this.schedulerSU(City4Mogu.SUZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 0 0 ? * *")
    public void scheduler4GZ(){
        this.schedulerGZ(City4Mogu.GUANGZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 0 23 ? * *")
    public void scheduler4ZZ(){
        this.schedulerZZ(City4Mogu.ZHENGZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 0 0 ? * *")
    public void scheduler4HZ(){
        this.schedulerHZ(City4Mogu.HANGZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 0 2 ? * *")
    public void scheduler4BJ(){
        this.schedulerBJ(City4Mogu.BEIJING.getCityShortName());
    }
    @Scheduled(cron = "0 30 1 ? * *")
    public void scheduler4NJ(){
        this.schedulerNJ(City4Mogu.NANJING.getCityShortName());
    }
    @Scheduled(cron = "0 30 1 ? * *")
    public void scheduler4TJ(){
        this.schedulerTJ(City4Mogu.TIANJIN.getCityShortName());
    }
    @Scheduled(cron = "0 0 2 ? * *")
    public void scheduler4WH(){
        this.schedulerWH(City4Mogu.WUHAN.getCityShortName());
    }

    private void schedulerSH(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Sh mogoCrawlerThread4Sh  = new MogoCrawlerThread4Sh();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Sh.setMap(param);
        new Thread(mogoCrawlerThread4Sh,shortName).start();
    }

    private void schedulerSZ(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Sz mogoCrawlerThread4Sz  = new MogoCrawlerThread4Sz();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Sz.setMap(param);
        new Thread(mogoCrawlerThread4Sz,shortName).start();
    }

    private void schedulerSU(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Su mogoCrawlerThread4Su  = new MogoCrawlerThread4Su();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Su.setMap(param);
        new Thread(mogoCrawlerThread4Su,shortName).start();
    }

    private void schedulerGZ(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Gz mogoCrawlerThread4Gz  = new MogoCrawlerThread4Gz();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Gz.setMap(param);
        new Thread(mogoCrawlerThread4Gz,shortName).start();
    }

    private void schedulerZZ(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Zz mogoCrawlerThread4Zz = new MogoCrawlerThread4Zz();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Zz.setMap(param);
        new Thread(mogoCrawlerThread4Zz,shortName).start();
    }

    private void schedulerHZ(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Hz mogoCrawlerThread4Hz  = new MogoCrawlerThread4Hz();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Hz.setMap(param);
        new Thread(mogoCrawlerThread4Hz,shortName).start();
    }

    private void schedulerBJ(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Bj mogoCrawlerThread4Bj  = new MogoCrawlerThread4Bj();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Bj.setMap(param);
        new Thread(mogoCrawlerThread4Bj,shortName).start();
    }

    private void schedulerNJ(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Nj mogoCrawlerThread4Nj  = new MogoCrawlerThread4Nj();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Nj.setMap(param);
        new Thread(mogoCrawlerThread4Nj,shortName).start();
    }

    private void schedulerTJ(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Tj mogoCrawlerThread4Tj  = new MogoCrawlerThread4Tj();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Tj.setMap(param);
        new Thread(mogoCrawlerThread4Tj,shortName).start();
    }

    private void schedulerWH(String shortName){
        log.info(shortName.toString());
        //苏州
        MogoCrawlerThread4Wh mogoCrawlerThread4Wh  = new MogoCrawlerThread4Wh();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        mogoCrawlerThread4Wh.setMap(param);
        new Thread(mogoCrawlerThread4Wh,shortName).start();
    }

}
