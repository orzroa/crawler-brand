package com.hizhu.crawler.brand.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/20 16:21
 */
public class UrlEnum {

    @Getter
    @AllArgsConstructor
    public enum Beike{

        URL_HEAD("https://","url头"),
        HOME_URL(".zu.ke.com/","主页通用url"),
        RENT_FLAG("/zufang/","租房url"),
        PAGE_FLAG("/pg","翻页url");

        private String url;
        private String explain;

    }



}
