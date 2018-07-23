package com.hizhu.crawler.brand.quartz.mogo;

import com.hizhu.crawler.brand.common.FetchedPageInfo;
import com.hizhu.crawler.brand.common.HttpConnectionManager;
import com.hizhu.crawler.brand.entity.bo.SaveBrandInfo;
import com.hizhu.crawler.brand.entity.enums.PlatformType;
import com.hizhu.crawler.brand.service.BrandInfoService;
import com.hizhu.crawler.brand.utils.LogService;
import com.hizhu.crawler.brand.utils.extract.MogoBrandUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/13 16:31
 */
@Slf4j
public class MogoCrawlerThread4Hz implements Runnable,LogService {
    private static final String HEAD_URL = "http://";
    private static final String START_TAIL_URL = ".mgzf.com/list/";

    private BrandInfoService brandInfoService;

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


    /**
     *  提取信息 封装到 Object Bean 里面
     * @param urlsSingle4Detail
     * @return
     */
    private static SaveBrandInfo getInfoFromDetailPage(String urlsSingle4Detail) {

        SaveBrandInfo saveBrandInfo = new SaveBrandInfo();
        FetchedPageInfo info4detail = HttpConnectionManager.sendGetAndRetry(urlsSingle4Detail, false, true, "", 1000);
        Document doc4Detail = Jsoup.parse(info4detail.getContent());

        //TODO 提取有效信息
        if (doc4Detail != null){
//            log.info("管家姓名:" + stewardName + ",品牌名称:" + brandName + " , 电话:" + telephone);
            final Elements parentElement = doc4Detail.select("div.person-wrap");
            final Elements select = parentElement.select("a.right");

            if (!CollectionUtils.isEmpty(select)){
                final String brandUrl = select.attr("href");
                final String brandName = parentElement.select("a.rd-brand-new-link").text();
                final String stewardName = parentElement.select("span.person-title").text();
                final String telephone = doc4Detail.select("p.phone-number").text();
                saveBrandInfo.setBrandUrl(brandUrl);
                saveBrandInfo.setBrandNameFromOut(brandName);
                saveBrandInfo.setPlatformType(PlatformType.MOGO);
                saveBrandInfo.setStewardName(stewardName);
                saveBrandInfo.setTelephone(telephone);
                return  saveBrandInfo;
            }
        }
        return  null;
    }
}
