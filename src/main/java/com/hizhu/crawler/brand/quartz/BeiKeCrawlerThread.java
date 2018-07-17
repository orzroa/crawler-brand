package com.hizhu.crawler.brand.quartz;

import com.hizhu.crawler.brand.common.FetchedPageInfo;
import com.hizhu.crawler.brand.common.HttpConnectionManager;
import com.hizhu.crawler.brand.entity.bo.SaveBrandInfo;
import com.hizhu.crawler.brand.entity.enums.City4BeiKe;
import com.hizhu.crawler.brand.entity.enums.PlatformType;
import com.hizhu.crawler.brand.service.BrandInfoService;
import com.hizhu.crawler.brand.utils.EnumUtil;
import com.hizhu.crawler.brand.utils.LogService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description ：贝壳品牌抓取线程
 *
 * @author： manji
 * 2018/7/10 16:47
 */
@Slf4j
public class BeiKeCrawlerThread  implements Runnable , LogService {

    private BrandInfoService brandInfoService;

    private static final Pattern TOTAL = Pattern.compile("\\bg_conf.total\\b.*");
    private static final Pattern TOTAL_PAGE = Pattern.compile("\\bg_conf.total_page\\b.*");
    private static final Pattern NUM = Pattern.compile("'.*'");
    private static final String MOBILE_BEFORE_URL = "https://m.ke.com/chuzu/";
    private static final String MOBILE_AFTER_URL = "/apartment";
    private static final String MOBILE_AFTER_URL_PAGE = "/apartment/pg";

    private static final String PC_BEFORE_URL_HEAD = "https://";
    private static final String PC_BEFORE_URL = ".zu.ke.com";

    public static Pattern getTotal() {
        return TOTAL;
    }
    public static Pattern getTotalPage() {
        return TOTAL_PAGE;
    }
    public static Pattern getNum() {
        return NUM;
    }

    static String cityShortName;

    public static String getCityShortName() {
        return cityShortName;
    }

    /**
     * 参数
     */
    @Setter
    private HashMap<String,Object> map;

    @Override
    public void run() {

        cityShortName = map.get("cityShortName").toString();
        final String threadName = map.get("threadName").toString();
        log.info("线程名称====>>>>" +threadName +  "; cityName ====>>>>" + cityShortName);



        brandInfoService = (BrandInfoService) map.get("brandInfoService");

        //起始Url example: "https://m.ke.com/chuzu/" + CITYSHORTNAME +"/apartment";
        String startUrl = MOBILE_BEFORE_URL + cityShortName + MOBILE_AFTER_URL;
        System.err.println("这个是 手机url ====>>>>" + startUrl);

        final FetchedPageInfo fetchedPageInfo = HttpConnectionManager.sendGetAndRetry(startUrl, true, true, "", 500);
        Document doc = Jsoup.parse(fetchedPageInfo.getContent());

        final Map<String, Integer> pageInfo = getPageNumInfo(doc);
        //总条数(目前没用到)  + 总页数
        final Integer total = pageInfo.get("total");
        final Integer totalPage = pageInfo.get("totalPage");
        log.info("当前城市" + cityShortName + ",总条数 ====>>>>" + total);

        //拼装detailsUrlList
        final ArrayList<String> urls = joint4DetailsUrl(pageInfo);

        //解析 详情页 提取信息 保存  Time Long
        for (String url: urls){
            // 根据detailUrl 获取详情
            SaveBrandInfo saveBrandInfo = getInfoFromDetailPage(url);
            final City4BeiKe cityEnum = EnumUtil.getByCityShortName(City4BeiKe.class, cityShortName);
            saveBrandInfo.setCityCode(cityEnum.getCityCode());
            saveBrandInfo.setCityName(cityEnum.getCityName());
            this.brandInfoService.saveBrandInfo(saveBrandInfo);
        }

    }//end run


    /**
     *  提取信息 封装到 Object Bean 里面
     * @param detailUrl
     * @return
     */
    private static SaveBrandInfo getInfoFromDetailPage(String detailUrl) {

        SaveBrandInfo saveBrandInfo = new SaveBrandInfo();

        FetchedPageInfo fetchedPageInfo = HttpConnectionManager.sendGetAndRetry(detailUrl, false, true, "", 1000);
        Document doc = Jsoup.parse(fetchedPageInfo.getContent());
        if (doc != null){
            final String stewardName = doc.select("#aside > div > ul > li > p.content__aside__list--title.oneline > span:nth-child(1)").text();
            final String brandName = doc.select("p.content__aside__list--subtitle").html();
            final String telephone = doc.select("p.content__aside__list--bottom").html();
            log.info("管家姓名:" + stewardName + ",品牌名称:" + brandName + " , 电话:" + telephone);
            saveBrandInfo.setBrandUrl(detailUrl);
            saveBrandInfo.setBrandNameFromOut(brandName);
            saveBrandInfo.setPlatformType(PlatformType.BEIKE);
            saveBrandInfo.setStewardName(stewardName);
            saveBrandInfo.setTelephone(telephone);
        }
        return  saveBrandInfo;
    }

    /**
     * 拼装 详情页面urlList
     * @param pageInfo
     * @return
     */
    private static ArrayList<String> joint4DetailsUrl(Map<String, Integer> pageInfo) {
        ArrayList<String> brandInfoList = new ArrayList<>();
        final Integer totalPage = pageInfo.get("totalPage");
        // 拼装 列表页 url { https://m.ke.com/chuzu/sh/apartment/pg20}
        for (int i = 1; i <= totalPage; i++) {
            //url 拼装
            String listUrl = MOBILE_BEFORE_URL + getCityShortName() + MOBILE_AFTER_URL_PAGE + i;
            log.info("列表页====>>>>::" + listUrl);
            final FetchedPageInfo fetchedPageInfoOne = HttpConnectionManager.sendGetAndRetry(listUrl, false, true, "", 500);

            Document doc1 = Jsoup.parse(fetchedPageInfoOne.getContent());
            //  获取所有 apartment 的 href example : <a href="/chuzu/sh/apartment/695.html">
            Elements elements = doc1.select("div.flat_item_card > a");

            elements.forEach(element -> {
                String href = element.attr("href");
                final String[] split = href.split(getCityShortName());
                // url  https://bj.zu.ke.com/apartment/2343.html
                String fullUrl = PC_BEFORE_URL_HEAD+ getCityShortName() + PC_BEFORE_URL + split[split.length - 1];
                brandInfoList.add(fullUrl);
            });
            System.out.println("第" + i + "页======>>>>>>>> 打印一个页面日志 <<<<<<<<========" + brandInfoList.get(0));

        }
        return brandInfoList;
    }

    /**
     * 根据 列表 Document >> Page的 源码 获取总条数(totalPage)
     * //TODO 可以优化正则   可以将城市名称 编码 shortName写进来 , 这样就不用从多个地方 获取数据
     * @param document
     * @return
     */
    public static Map<String, Integer> getPageNumInfo(Document document){

        //get4totalPage
        final Elements select = document.select("script");

        List<FetchedPageInfo> pageInfo = new ArrayList<>();
        Map<String, Integer> mapResult = new HashMap<>(16);

        loop1:
        for (Element s : select) {
            String text = s.html();
            Matcher totalMatch = getTotal().matcher(text);
            Matcher totalPageMatch = getTotalPage().matcher(text);
            while (totalMatch.find()) {
                String totalMatchStr = totalMatch.group();
                Matcher matcher = getNum().matcher(totalMatchStr);
                while (matcher.find()) {
                    String totalStr = matcher.group();
                    final String total = totalStr.replaceAll("'", "");
                    mapResult.put("total", Integer.parseInt(total));
                }
            }
            while (totalPageMatch.find()) {
                String totalMatchStr = totalPageMatch.group();
                Matcher matcher = getNum().matcher(totalMatchStr);
                while (matcher.find()) {
                    String totalPageStr = matcher.group();
                    final String totalPage = totalPageStr.replaceAll("'", "");
                    mapResult.put("totalPage", Integer.parseInt(totalPage));
                }
            }
            while (!CollectionUtils.isEmpty(pageInfo)) {
                break loop1;
            }
        }
        return mapResult;
    }

}
