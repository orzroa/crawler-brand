package com.hizhu.crawler.brand.test;

import com.hizhu.crawler.brand.common.FetchedPageInfo;
import com.hizhu.crawler.brand.common.HttpConnectionManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/20 16:58
 */
public class TestBeiKeUtils4Pc {


    public static void main(String[] args) {

        /*String paramUrl = "https://sh.zu.ke.com";
        final CopyOnWriteArraySet<String> allUrls4Area = BeiKeUtils4Pc.getAllUrl4Area(paramUrl);
        allUrls4Area.forEach(singleUrl4Area->{
            System.out.println(singleUrl4Area);
        });*/

        /*CopyOnWriteArraySet<String> allUrls4Page = new CopyOnWriteArraySet<>();

        String url = "https://sh.zu.ke.com/zufang/jingan/";
        FetchedPageInfo urlStrInfo = HttpConnectionManager.sendGetAndRetry(url,false,true,"utf-8",500);
        final Document document = Jsoup.parse(urlStrInfo.getContent());
        final String totalPageStr = document.select("div.content__pg").attr("data-totalPage");
        final int totalPage = Integer.parseInt(totalPageStr);
        System.out.println(totalPageStr);

        for (int i=1 ; i <= totalPage ; i++){
            String singleUrl4Page = url + UrlEnum.Beike.PAGE_FLAG.getUrl() + i;
            allUrls4Page.add(singleUrl4Page);
        }*/
//https://sh.zu.ke.com/zufang/jingan//pg25
        String detailUrl = "https://sh.zu.ke.com/zufang/jingan//pg25";
        FetchedPageInfo info = HttpConnectionManager.sendGetAndRetry(detailUrl,false,true,"utf-8",500);
        final Document doc = Jsoup.parse(info.getContent());


//        allUrls4Page.forEach(singleUrl4Page->{
//            System.out.println(singleUrl4Page);
//            FetchedPageInfo info = HttpConnectionManager.sendGetAndRetry(singleUrl4Page,false,true,"utf-8",500);
//        });

    }
}
