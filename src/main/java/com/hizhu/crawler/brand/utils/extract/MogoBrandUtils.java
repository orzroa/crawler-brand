package com.hizhu.crawler.brand.utils.extract;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hizhu.crawler.brand.common.FetchedPageInfo;
import com.hizhu.crawler.brand.common.HttpConnectionManager;
import com.hizhu.crawler.brand.entity.bo.SaveBrandInfo;
import com.hizhu.crawler.brand.entity.enums.City4Mogu;
import com.hizhu.crawler.brand.entity.enums.PlatformType;
import com.hizhu.crawler.brand.service.BrandInfoService;
import com.hizhu.crawler.brand.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description ：蘑菇品牌信息 提取工具
 *
 * @author： manji
 * 2018/7/13 14:31
 */
@Slf4j
public class MogoBrandUtils {

    /**
     * Page 从1 开始
     */
    private static  Integer totalPage = 1;
    /**
     * 总条数 默认为0, 很多城市 的二级 三级区域 没有房源
     */
    private static  Integer total = 0;

    private static BrandInfoService brandInfoService;

    /**
     * 根据 startUrl 获取地域 urlList
     * @param startUrl
     * @return
     */
    public static CopyOnWriteArraySet<String> getAllUrl4AreaList(String startUrl) {
        if (StringUtils.isEmpty(startUrl)){
            log.info("方法：MogoBrandUtils.getAllUrl4AreaList ==>> startUrl 为 null");
            return null;
        }
        CopyOnWriteArraySet<String> urlsAll4Area = new CopyOnWriteArraySet<>();
        FetchedPageInfo fetchedPageInfo = HttpConnectionManager.sendGetAndRetry(startUrl, false, true, "", 1000);
        // 获得html内容
        Document doc = Jsoup.parse(fetchedPageInfo.getContent());
        // 获取地域集合
        Elements districtElements = doc.select("div.region-list").select("span.item > a");
        Elements areaElements = doc.select("div.region-list").select("div.row-expand > a");

        //拼装列表页 URLList  >>>====<<<  Begin
        districtElements.forEach(districtElement ->{
            String listUrl4Area = startUrl + districtElement.attr("code");
            urlsAll4Area.add(listUrl4Area);
        });
        areaElements.forEach(areaElement -> {
            String listUrl4Area = startUrl + areaElement.attr("code");
            urlsAll4Area.add(listUrl4Area);
        });
        return urlsAll4Area;
    }

    /**
     * 根据所有地区AreaUrl { su.mgzf.com/list/xz1518_gusuqu } 获取 AreaPageUrl { su.mgzf.com/list/xz1518_gusuqu?page=1 }
     * @param urlsAllArea
     * @return
     */
    public static CopyOnWriteArraySet<String> getAllUrl4PageList(CopyOnWriteArraySet<String> urlsAllArea) {

        CopyOnWriteArraySet<String> urlsAll4Page = new CopyOnWriteArraySet<>();
        if (urlsAllArea == null){
            log.info("方法：MogoBrandUtils.getAllUrl4DetailList ==>> urlsAllArea 为 null");
         return null;
        }
        urlsAllArea.forEach(urlSingle ->{
            /**
             * 提取列表页面Page 信息  >>>====<<<  Begin
             */
            FetchedPageInfo urlStrInfo = HttpConnectionManager.sendPostAndRetry(urlSingle, null,null,true,500);
            if (urlStrInfo != null){
                urlsAllArea.remove(urlSingle);
            }
            Document urlStrDoc = Jsoup.parse(urlStrInfo.getContent());
            String body = urlStrDoc.select("body").text();
            if (StringUtils.isNoneBlank(body)){
                try {
                    JSONObject jsonObject = JSONObject.parseObject(body);
                    String pageNumStr = jsonObject.getString("totalPage");
                    String totalNumStr = jsonObject.getString("totalResult");
                    totalPage = Integer.parseInt(pageNumStr);
                    total = Integer.parseInt(totalNumStr);
                } catch (NumberFormatException e) {
                    log.info("mmp  >>>>>>> info4Json <<<<<<<");
                    urlsAllArea.add(urlSingle);
                }
            }

            if (total > 0){
                for (int currentPage = 1; currentPage <= totalPage; currentPage++){
                    String urlsSingle4Page = urlSingle + "?page=" + currentPage;
                    urlsAll4Page.add(urlsSingle4Page);
                }
            }
        });
        return urlsAll4Page;
    }

    /**
     *  根据 PageUrls  { http://tj.mgzf.com/list/xz2427_hedongqu?page=1 } 获取 DetailUrls { http://tj.mgzf.com/room/8998613.shtml }
     * @param urlsAll4Page
     * @return
     */
    public static CopyOnWriteArraySet<String> getAllDetailUrlList(CopyOnWriteArraySet<String> urlsAll4Page){
        String cityName = "www";
        if (urlsAll4Page == null){
            log.info("方法：MogoBrandUtils.getAllDetailUrlList ==>> urlsAll4Page 为 null");
            return null;
        }
        CopyOnWriteArraySet<String> urlsAll4Detail = new CopyOnWriteArraySet<>();

        final String  url4CityName = (String) urlsAll4Page.toArray()[0];
        if (StringUtils.isNoneBlank(url4CityName)){
            String[] split = url4CityName.split("//");
            final String substring = split[split.length - 1].substring(0, 2);
            /**
             * 上海切出来是 ww
             */
            if (!"ww".equals(substring)){
                cityName = substring;
            }
        }
        //for foreach
        String finalCityName = cityName;

        urlsAll4Page.forEach(urlsSingle4Page->{
            FetchedPageInfo detailPageInfo = HttpConnectionManager.sendPostAndRetry(urlsSingle4Page, null,null,true,500);
            if (detailPageInfo != null){
                urlsAll4Page.remove(urlsSingle4Page);
            }

            Document detailDoc = Jsoup.parse(detailPageInfo.getContent());
            String body = detailDoc.select("body").text();
            // 每一个Body 对应一个区域中的一个Page1...2...3...4
            if (StringUtils.isNoneBlank(body)){
                JSONArray roomInfos = null;
                try {
                    JSONObject jsonObject = JSONObject.parseObject(body);
                    roomInfos = jsonObject.getJSONArray("roomInfos");
                } catch (Exception e) {
                    log.error("该死的JSON ,报错!!!!   >>>>>  =====   <<<<< 后面是出错url ::" +urlsSingle4Page);
                    urlsAll4Page.add(urlsSingle4Page);
                }
                if (roomInfos == null){
                    return;
                }
                // >>>>> 拼装所有房源信息 URL Begin <<<<<
                roomInfos.forEach(roomInfo->{
                    JSONObject roomInfo2JsonObj = (JSONObject)roomInfo;
                    String sourceType = roomInfo2JsonObj.getString("sourceType");
                    if ("2".equals(sourceType)){
                        String roomId = roomInfo2JsonObj.getString("roomId");
                        // urlFinal >>>>  http://su.mgzf.com/room/6675684.shtml
                        String urlFinal = "http://"+ finalCityName + ".mogoroom.com/room/" + roomId +  ".shtml";
                        urlsAll4Detail.add(urlFinal);
                    }
                });
            }
        });
        return  urlsAll4Detail;
    }


    /**
     *
     * @param urlsAll4Detail-房源详情页URL 集合
     * @param map4Params-需要使用到的参数,譬如存储service
     */
    public static  void extractAndSaveBrandInfo(CopyOnWriteArraySet<String> urlsAll4Detail , HashMap<String,Object> map4Params){

        if (urlsAll4Detail == null){
            log.info("方法：MogoBrandUtils.extractAndSaveBrandInfo ==>> urlsAll4Detail 为 null");
            return ;
        }

        String cityShortName = map4Params.get("cityShortName").toString();
        final String threadName = map4Params.get("threadName").toString();
        brandInfoService = (BrandInfoService) map4Params.get("brandInfoService");

        urlsAll4Detail.forEach(detailUrl->{

            SaveBrandInfo saveBrandInfo = new SaveBrandInfo();
            try {
                FetchedPageInfo fetchedPageInfo4Post = HttpConnectionManager.sendPostAndRetry(detailUrl, null,null,true,500);
                urlsAll4Detail.remove(detailUrl);
                Document doc4Post = Jsoup.parse(fetchedPageInfo4Post.getContent());
                final Elements elements = doc4Post.select("div.room-manager").select("a");
                if (elements.size() < 1){
                    return;
                }
                String urlFromExtract = elements.attr("href").replaceAll("//","");
                String brandFinalUrl = urlFromExtract + "/" + cityShortName;

                final String body = doc4Post.select("body > script:nth-child(2)").html();
                final String[] split = body.split("__NUXT__=");
                final String json = split[1].toString();
                final String subJson = json.substring(0, json.length() - 1);

                final Object data = JSONObject.parseObject(subJson).get("data");
                final Object basicInfo = ((JSONObject) ((JSONArray) data).get(0)).get("basicInfo");
                final Object brandInfo = ((JSONObject) basicInfo).get("brandInfo");

                final String brandName = (String)((JSONObject) brandInfo).get("brandName");
                final String landlordName = (String)((JSONObject) brandInfo).get("landlordName");
                final String phoneNum = (String)((JSONObject) brandInfo).get("phoneNum");

                saveBrandInfo.setPlatformType(PlatformType.MOGO);
                saveBrandInfo.setBrandNameFromOut(brandName);
                saveBrandInfo.setBrandUrl(brandFinalUrl);
                saveBrandInfo.setStewardName(landlordName);
                saveBrandInfo.setTelephone(phoneNum);
                final City4Mogu cityEnum = EnumUtil.getByCityShortName(City4Mogu.class, cityShortName);
                saveBrandInfo.setCityName(cityEnum.getCityName());
                saveBrandInfo.setCityCode(cityEnum.getCityCode());

            } catch (Exception e) {
                urlsAll4Detail.add(detailUrl);
            }
            brandInfoService.saveBrandInfo(saveBrandInfo);
        });// end >> urlsAll4Detail.forEach

        log.info("====>>>>>> 线程名称  >>" + threadName +" 解析完成>>>>");
    }

}



