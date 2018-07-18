package com.hizhu.crawler.brand.quartz;

import com.hizhu.crawler.brand.entity.enums.City4BeiKe;
import com.hizhu.crawler.brand.quartz.beike.*;
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


    @Scheduled(cron = "0 30 22 ? * *")
    public void scheduler4SH(){
        this.schedulerSH(City4BeiKe.SHANGHAI.getCityShortName());
    }
    @Scheduled(cron = "0 30 22 ? * *")
    public void scheduler4SZ(){
        this.schedulerSZ(City4BeiKe.SHENZHENG.getCityShortName());
    }
    @Scheduled(cron = "0 30 22 ? * *")
    public void scheduler4SU(){
        this.schedulerSU(City4BeiKe.SUZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 32 22 ? * *")
    public void scheduler4GZ(){
        this.schedulerGZ(City4BeiKe.GUANGZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 32 22 ? * *")
    public void scheduler4ZZ(){
        this.schedulerZZ(City4BeiKe.ZHENGZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 32 22 ? * *")
    public void scheduler4HZ(){
        this.schedulerHZ(City4BeiKe.HANGZHOU.getCityShortName());
    }
    @Scheduled(cron = "0 34 22 ? * *")
    public void scheduler4BJ(){
        this.schedulerBJ(City4BeiKe.BEIJING.getCityShortName());
    }
    @Scheduled(cron = "0 36 22 ? * *")
    public void scheduler4NJ(){
        this.schedulerNJ(City4BeiKe.NANJING.getCityShortName());
    }
    @Scheduled(cron = "0 36 22 ? * *")
    public void scheduler4TJ(){
        this.schedulerTJ(City4BeiKe.TIANJIN.getCityShortName());
    }
    @Scheduled(cron = "0 38 22 ? * *")
    public void scheduler4WH(){
        this.schedulerWH(City4BeiKe.WUHAN.getCityShortName());
    }


    private void schedulerSH(String shortName){
        log.info(shortName.toString());
        //上海
        BeiKeBrandCrawler4Sh keBrandCrawler4Sh  = new BeiKeBrandCrawler4Sh();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Sh.setMap(param);
        new Thread(keBrandCrawler4Sh,shortName).start();
    }

    private void schedulerSZ(String shortName){
        log.info(shortName.toString());
        //深圳
        BeiKeBrandCrawler4Sz keBrandCrawler4Sz  = new BeiKeBrandCrawler4Sz();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Sz.setMap(param);
        new Thread(keBrandCrawler4Sz,shortName).start();
    }

    private void schedulerSU(String shortName){
        log.info(shortName.toString());
        //苏州
        BeiKeBrandCrawler4Su keBrandCrawler4Su  = new BeiKeBrandCrawler4Su();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Su.setMap(param);
        new Thread(keBrandCrawler4Su,shortName).start();
    }

    private void schedulerGZ(String shortName){
        log.info(shortName.toString());
        //广州
        BeiKeBrandCrawler4Gz keBrandCrawler4Gz  = new BeiKeBrandCrawler4Gz();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Gz.setMap(param);
        new Thread(keBrandCrawler4Gz,shortName).start();
    }

    private void schedulerZZ(String shortName){
        log.info(shortName.toString());
        //郑州
        BeiKeBrandCrawler4Zz keBrandCrawler4Zz = new BeiKeBrandCrawler4Zz();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Zz.setMap(param);
        new Thread(keBrandCrawler4Zz,shortName).start();
    }

    private void schedulerHZ(String shortName){
        log.info(shortName.toString());
        //杭州
        BeiKeBrandCrawler4Hz keBrandCrawler4Hz  = new BeiKeBrandCrawler4Hz();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Hz.setMap(param);
        new Thread(keBrandCrawler4Hz,shortName).start();
    }

    private void schedulerBJ(String shortName){
        log.info(shortName.toString());
        //北京
        BeiKeBrandCrawler4Bj keBrandCrawler4Bj  = new BeiKeBrandCrawler4Bj();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Bj.setMap(param);
        new Thread(keBrandCrawler4Bj,shortName).start();
    }

    private void schedulerNJ(String shortName){
        log.info(shortName.toString());
        //南京
        BeiKeBrandCrawler4Nj keBrandCrawler4Nj  = new BeiKeBrandCrawler4Nj();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Nj.setMap(param);
        new Thread(keBrandCrawler4Nj,shortName).start();
    }

    private void schedulerTJ(String shortName){
        log.info(shortName.toString());
        //天津
        BeiKeBrandCrawler4Tj keBrandCrawler4Tj  = new BeiKeBrandCrawler4Tj();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Tj.setMap(param);
        new Thread(keBrandCrawler4Tj,shortName).start();
    }

    private void schedulerWH(String shortName){
        log.info(shortName.toString());
        //武汉
        BeiKeBrandCrawler4Wh keBrandCrawler4Wh  = new BeiKeBrandCrawler4Wh();
        HashMap<String,Object> param = new HashMap<>(32);
        param.put("brandInfoService",brandInfoService);
        param.put("threadName",  "Thread：" + shortName);
        param.put("cityShortName", shortName);
        keBrandCrawler4Wh.setMap(param);
        new Thread(keBrandCrawler4Wh,shortName).start();
    }

    /**
     *  delete
     * @param shortName
     */
//    private void scheduler(String shortName){
//        log.info(shortName.toString());
//        //上海
//        for (int i = 0; i < MagicNum.N_6; i ++){
//            BeiKeCrawlerThread beikeBrandThread = new BeiKeCrawlerThread();
//            HashMap<String,Object> param = new HashMap<>(32);
//            param.put("brandInfoService",brandInfoService);
//            param.put("threadName",  "Thread：" + shortName);
//            param.put("cityShortName", shortName);
//            beikeBrandThread.setMap(param);
//            new Thread(beikeBrandThread,shortName).start();
//        }
//
//    }



}
