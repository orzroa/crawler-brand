package com.hizhu.crawler.brand.entity.bo;

import com.hizhu.crawler.brand.entity.enums.PlatformType;
import lombok.Data;

/**
 * Description ：品牌信息 {保存}
 *
 * @author： manji
 * 2018/7/3 16:23
 */
@Data
public class SaveBrandInfo {

    /**
     * 品牌Id
     */
    private Integer brandId;
    /**
     * 平台类型
     */
    private PlatformType platformType;
    /**
     * 品牌名称(外部平台)
     */
    private String brandNameFromOut;
    /**
     * 品牌主页(外部平台地址)
     */
    private String brandUrl;
    /**
     * 管家名称
     */
    private String stewardName;
    /**
     * 管家电话
     */
    private String telephone;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 城市编码
     */
    private String cityCode;


}
