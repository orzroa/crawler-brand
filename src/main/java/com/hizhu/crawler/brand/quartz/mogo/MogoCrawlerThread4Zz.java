package com.hizhu.crawler.brand.quartz.mogo;

import com.hizhu.crawler.brand.service.BrandInfoService;
import com.hizhu.crawler.brand.utils.LogService;
import com.hizhu.crawler.brand.utils.extract.MogoBrandUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/13 16:31
 */
@Slf4j
public class MogoCrawlerThread4Zz implements Runnable,LogService {
    private static final String HEAD_URL = "http://";
    private static final String START_TAIL_URL = ".mgzf.com/list/";

    private BrandInfoService brandInfoService;

    static String cityShortName;

    public static String getCityShortName() {
        return cityShortName;
    }

    @Getter
    @Setter
    private HashMap<String,Object> map;

    @Override
    public void run() {

        cityShortName = map.get("cityShortName").toString();
        final String threadName = map.get("threadName").toString();
        brandInfoService = (BrandInfoService) map.get("brandInfoService");

        //// StartUrl  http://tj.mgzf.com/list/
        String startUrl = HEAD_URL + cityShortName +  START_TAIL_URL;

        log.info("线程名称是：" + threadName + ">>>> 开始爬取 <<<<城市："+ cityShortName + "===== 起始URL为 >>>>" + startUrl);

        CopyOnWriteArraySet<String> urlsAll4Area = MogoBrandUtils.getAllUrl4Area(startUrl);
        log.info("城市名称：" + cityShortName + ", 地区url数量：" +  urlsAll4Area.size());

        CopyOnWriteArraySet<String> urlsAll4Page = MogoBrandUtils.getAllUrl4Page(urlsAll4Area);
        log.info("城市名称：" + cityShortName + ", 分页url数量：" +  urlsAll4Page.size());

        CopyOnWriteArraySet<String> urlsAll4Detail = MogoBrandUtils.getAllDetailUrl(urlsAll4Page);
        log.info("城市名称：" + cityShortName + ", 详情url数量：" +  urlsAll4Detail.size());

        /**
         * 解析房源详情
         */
        MogoBrandUtils.extractAndSaveBrandInfo(urlsAll4Detail,map);

    }

}
