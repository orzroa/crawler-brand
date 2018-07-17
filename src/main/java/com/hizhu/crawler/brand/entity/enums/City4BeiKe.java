package com.hizhu.crawler.brand.entity.enums;

import lombok.*;

/**
 * Description ：贝壳平台,城市信息
 *
 * @author： manji
 * 2018/7/10 14:16
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum City4BeiKe {

    //12
    SUZHOU("001010013","320500","su","SUZHOU"),
    // 358
    GUANGZHOU("001019001","440100","gz","GUANGZHOU"),
    // 675
    SHANGHAI("001009001","310000","sh","SHANGHAI"),
    //261
    BEIJING("001001","110000","bj","BEIJING"),
    //236
    HANGZHOU("001011001","330100","hz","HANGZHOU"),
    // 136
    NANJING("001010001","320100","nj","NANJING"),
    // 921
    SHENZHENG("001019002","440300","sz","SHENZHENG"),
    //NONE
    ZHENGZHOU("001016001","410100","zz","ZHENGZHOU"),
    // 29
    WUHAN("001017001","420100","wh","WUHAN"),
    //10
    TIANJIN("001002001","120000","tj","TIANJIN")
    ;

    /**
     * 城市编码_嗨住
     */
    private String cityCode;
    /**
     * 城市编码_外部平台 ( 贝壳 )
     */
    private String cityCodeOut;
    /**
     * 城市缩写
     */
    private String cityShortName;
    /**
     * 城市名称
     */
    private String cityName;


}
