package com.hizhu.crawler.brand.quartz.beike;

import com.hizhu.crawler.brand.utils.extract.BeiKeBrandUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/18 15:41
 */
@Slf4j
public class BeiKeBrandCrawler4Bj implements Runnable{

    private static final String MOBILE_BEFORE_URL = "https://m.ke.com/chuzu/";
    private static final String MOBILE_AFTER_URL = "/apartment";

    static String cityShortName;

    public static String getCityShortName() {
        return cityShortName;
    }

    @Setter
    private HashMap<String,Object> map;

    @Override
    public void run() {

        cityShortName = map.get("cityShortName").toString();
        final String threadName = map.get("threadName").toString();

        log.info("线程名称====>>>>" +threadName +  "; cityName ====>>>>" + cityShortName);

        //起始Url example: "https://m.ke.com/chuzu/" + CITYSHORTNAME +"/apartment";
        String startUrl = MOBILE_BEFORE_URL + cityShortName + MOBILE_AFTER_URL;

        final CopyOnWriteArraySet<String> urlsAll4Detail = BeiKeBrandUtils.getAllUrl4DetailList(startUrl);

        //解析 详情页 提取信息 保存  Time Long
        BeiKeBrandUtils.extractAndSaveBrandInfo(urlsAll4Detail , map);

    }//end run
}
