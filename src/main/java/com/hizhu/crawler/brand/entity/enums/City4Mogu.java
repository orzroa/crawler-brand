package com.hizhu.crawler.brand.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description ：蘑菇平台,城市信息
 *
 * @author： manji
 * 2018/7/10 14:36
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum City4Mogu {


    SHANGHAI("001009001","289","sh","SHANGHAI"),
    SHENZHENG("001019002","340","sz","SHENZHENG"),

    SUZHOU("001010013","224","su","SUZHOU"),
    GUANGZHOU("001019001","257","gz","GUANGZHOU"),
    HANGZHOU("001011001","179","hz","HANGZHOU"),
    ZHENGZHOU("001016001","268","zz","ZHENGZHOU"),

    BEIJING("001001","131","bj","BEIJING"),
    NANJING("001010001","315","nj","NANJING"),
    TIANJIN("001002001","332","tj","TIANJIN"),

    WUHAN("001017001","218","wh","WUHAN")
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
