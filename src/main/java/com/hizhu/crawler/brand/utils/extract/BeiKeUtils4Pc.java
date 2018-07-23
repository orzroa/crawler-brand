package com.hizhu.crawler.brand.utils.extract;

import com.hizhu.crawler.brand.common.FetchedPageInfo;
import com.hizhu.crawler.brand.common.HttpConnectionManager;
import com.hizhu.crawler.brand.entity.enums.UrlEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/20 16:19
 */
@Slf4j
public class BeiKeUtils4Pc {

    /**
     * 根据startUrl 获取 区域urlList
     * @param startUrl
     * @return
     */
    public static CopyOnWriteArraySet<String> getAllUrl4Area(String startUrl){

        if (StringUtils.isEmpty(startUrl)){
            log.info("方法：BeiKeUtils4Pc.getAllUrl4Area ==>> startUrl 为 null");
            return null;
        }

        //方便处理 地区url 的拼接
        final String beginUrl = startUrl + UrlEnum.Beike.RENT_FLAG.getUrl();

        CopyOnWriteArraySet<String> allUrls4Area = new CopyOnWriteArraySet<>();

        final FetchedPageInfo fetchedPageInfo = HttpConnectionManager.sendGetAndRetry(beginUrl, false, true, "", 500);

        Document doc = Jsoup.parse(fetchedPageInfo.getContent());

        final Elements elements4Area = doc.select("#filter > ul:nth-child(2) > li> a");

        elements4Area.forEach(element -> {
            final String href4Area = element.attr("href");
            String singleUrl4Area = startUrl + href4Area;
            allUrls4Area.add(singleUrl4Area);
        });

        return allUrls4Area;
    }

    /**
     *  根据区域url 获取 带有分页的url
     * @param allUrls4Area
     * @return
     */
    public static CopyOnWriteArraySet<String> getAllUrl4Page(CopyOnWriteArraySet<String> allUrls4Area){

        if (allUrls4Area == null){
            log.info("方法：BeiKeUtils4Pc.getAllUrl4Page ==>> allUrls4Area 为 null");
            return null;
        }
        CopyOnWriteArraySet<String> allUrls4Page = new CopyOnWriteArraySet<>();

        allUrls4Area.forEach(singleUrl4Area->{
            /**
             * 提取列表页面Page 信息  >>>====<<<  Begin
             */
            FetchedPageInfo urlStrInfo = HttpConnectionManager.sendGetAndRetry(singleUrl4Area,false,true,"utf-8",500);
            if (urlStrInfo != null){
                allUrls4Area.remove(singleUrl4Area);
            }
            Integer totalPage = 0;
            try {
                final Document document = Jsoup.parse(urlStrInfo.getContent());
                final String totalPageStr = document.select("div.content__pg").attr("data-totalPage");
                totalPage = Integer.valueOf(totalPageStr);
            } catch (NumberFormatException e) {
                log.error(">> error << 获取区域对应 分页url 解析错误 , url >> " + singleUrl4Area);
               allUrls4Area.add(singleUrl4Area);
            }
            if (totalPage < 1){
                return;
            }
            for (int i=1 ; i <= totalPage ; i++){
                String singleUrl4Page = singleUrl4Area + UrlEnum.Beike.PAGE_FLAG.getUrl() + i;
                allUrls4Page.add(singleUrl4Page);
            }
        });
        return  allUrls4Page;
    }

    /**
     *  根据
     * @param allUrls4Page
     * @return
     */
    public static  CopyOnWriteArraySet<String> getAllUrl4Detail(CopyOnWriteArraySet<String> allUrls4Page){

        if (allUrls4Page == null){
            log.info("方法：BeiKeUtils4Pc.getAllUrl4Detail ==>> allUrls4Page 为 null");
            return null;
        }
        CopyOnWriteArraySet<String> allUrls4Detail = new CopyOnWriteArraySet<>();




        return  allUrls4Detail;
    }











}
