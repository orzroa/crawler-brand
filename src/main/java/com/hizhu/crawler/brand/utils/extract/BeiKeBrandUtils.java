package com.hizhu.crawler.brand.utils.extract;

import com.hizhu.crawler.brand.common.FetchedPageInfo;
import com.hizhu.crawler.brand.common.HttpConnectionManager;
import com.hizhu.crawler.brand.entity.bo.SaveBrandInfo;
import com.hizhu.crawler.brand.entity.enums.City4BeiKe;
import com.hizhu.crawler.brand.entity.enums.PlatformType;
import com.hizhu.crawler.brand.service.BrandInfoService;
import com.hizhu.crawler.brand.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description ：贝壳品牌信息 提取工具
 *
 * @author： manji
 * 2018/7/16 10:38
 */
@Slf4j
public class BeiKeBrandUtils {


//    StartUrl ==> https://m.ke.com/chuzu/sh/apartment
//    ForGet >  total  &  total_page
//
//    拼装url
//    PageUrlList ==> https://m.ke.com/chuzu/sh/apartment/pg20
//    ForGet > <a href="/chuzu/sh/apartment/540.html"> (div.flat_item_card)
//
//    拼装url
//    DetailUrlList ==> https://bj.zu.ke.com/apartment/2343.html
//    ForGet > detail & save

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

    private static BrandInfoService brandInfoService;

    /**
     * 根据startUrl 获取对应城市 分页urlList
     *
     * @param startUrl
     * @return
     */
    public static CopyOnWriteArraySet<String> getAllUrl4DetailList(String startUrl) {

        /**
         * 切割 startUrl 获取城市 短名称
         */
        final String[] splitStr = startUrl.split("https://m.ke.com/chuzu/");
        String cityShortName  = splitStr[splitStr.length - 1].substring(0,2);


        CopyOnWriteArraySet<String> urlsAll4Detail = new CopyOnWriteArraySet<>();

        final FetchedPageInfo fetchedPageInfo = HttpConnectionManager.sendGetAndRetry(startUrl, true, true, "", 500);
        Document document = Jsoup.parse(fetchedPageInfo.getContent());

        //get4totalPage
        final Elements elements4TotalPage = document.select("script");

        Map<String, Integer> mapResult = new HashMap<>(16);

        elements4TotalPage.forEach(element->{
            String text = element.html();
            Matcher totalMatch = getTotal().matcher(text);
            Matcher totalPageMatch = getTotalPage().matcher(text);
            // total 非必要解析
            while (totalMatch.find()) {
                String totalMatchStr = totalMatch.group();
                Matcher matcher = getNum().matcher(totalMatchStr);
                //
                while (matcher.find()) {
                    String totalStr = matcher.group();
                    final String total = totalStr.replaceAll("'", "");
                    mapResult.put("total", Integer.parseInt(total));
                }
            }
            while (totalPageMatch.find()) {
                String totalMatchStr = totalPageMatch.group();
                Matcher matcher = getNum().matcher(totalMatchStr);
                //
                while (matcher.find()) {
                    String totalPageStr = matcher.group();
                    final String totalPage = totalPageStr.replaceAll("'", "");
                    mapResult.put("totalPage", Integer.parseInt(totalPage));
                }
            }
            while (!CollectionUtils.isEmpty(mapResult)) {
                return;
            }
        }); // end

        final Integer total = mapResult.get("total");
        final Integer totalPage = mapResult.get("totalPage");
        log.info("当前城市 >>" + cityShortName + ",总条数 ====>>>>" + total + ", 总页数 ====>>>>" + totalPage);

        for (int i = 1; i <= totalPage; i++) {
            //url 拼装
            String listUrl = MOBILE_BEFORE_URL + cityShortName + MOBILE_AFTER_URL_PAGE + i;

            final FetchedPageInfo fetchedPageInfoOne = HttpConnectionManager.sendGetAndRetry(listUrl, false, true, "", 500);

            Document doc1 = Jsoup.parse(fetchedPageInfoOne.getContent());
            //  获取所有 apartment 的 href example : <a href="/chuzu/sh/apartment/695.html">
            Elements elements4Page = doc1.select("div.flat_item_card > a");

            elements4Page.forEach(element -> {
                String href = element.attr("href");
                final String[] split = href.split(cityShortName);
                // url  https://bj.zu.ke.com/apartment/2343.html
                String fullUrl = PC_BEFORE_URL_HEAD+  cityShortName  + PC_BEFORE_URL + split[split.length - 1];
                urlsAll4Detail.add(fullUrl);
            });
        }// end for
        return urlsAll4Detail;
    } // end method

    /**
     *
     * @param urlsAll4Detail-房源详情页URL 集合
     * @param map4Params-需要使用到的参数,譬如存储service
     */
    public static  void  extractAndSaveBrandInfo(CopyOnWriteArraySet<String> urlsAll4Detail , HashMap<String,Object> map4Params) {
        if (urlsAll4Detail == null){
            log.info("方法：BeiKeBrandUtils.extractAndSaveBrandInfo ==>> urlsAll4Detail 为 null");
            return ;
        }

        String cityShortName = map4Params.get("cityShortName").toString();
        final String threadName = map4Params.get("threadName").toString();
        brandInfoService = (BrandInfoService) map4Params.get("brandInfoService");

        urlsAll4Detail.forEach( detailUrl->{

            SaveBrandInfo saveBrandInfo = new SaveBrandInfo();
            // 根据detailUrl 获取详情
            try {
                FetchedPageInfo fetchedPageInfo = HttpConnectionManager.sendGetAndRetry(detailUrl, false, true, "", 1000);
                urlsAll4Detail.remove(detailUrl);
                Document doc = Jsoup.parse(fetchedPageInfo.getContent());
                if (doc != null){
                    final String stewardName = doc.select("#aside > div > ul > li > p.content__aside__list--title.oneline > span:nth-child(1)").text();
                    final String brandName = doc.select("p.content__aside__list--subtitle").html();
                    final String telephone = doc.select("p.content__aside__list--bottom").html();
//                    log.info("管家姓名:" + stewardName + ",品牌名称:" + brandName + " , 电话:" + telephone);
                    saveBrandInfo.setBrandUrl(detailUrl);
                    saveBrandInfo.setBrandNameFromOut(brandName);
                    saveBrandInfo.setPlatformType(PlatformType.BEIKE);
                    saveBrandInfo.setStewardName(stewardName);
                    saveBrandInfo.setTelephone(telephone);
                }
                final City4BeiKe cityEnum = EnumUtil.getByCityShortName(City4BeiKe.class, cityShortName);
                saveBrandInfo.setCityCode(cityEnum.getCityCode());
                saveBrandInfo.setCityName(cityEnum.getCityName());
            } catch (Exception e) {
                urlsAll4Detail.add(detailUrl);
            }
            brandInfoService.saveBrandInfo(saveBrandInfo);
        });  // end urlsAll4Detail.forEach

        log.info("====>>>>>> 线程名称  >>" + threadName +" 解析完成>>>>");

    }// end method


}


