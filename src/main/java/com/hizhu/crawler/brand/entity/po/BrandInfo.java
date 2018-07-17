package com.hizhu.crawler.brand.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * Description ：品牌信息：gaodu.brand_info
 *
 * @author： manji
 * 2018/7/3 10:02
 */
@Data
public class BrandInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 品牌Id
     */
    private Integer id;
    /**
     * 平台类型  详细见枚举 PlatformType
     */
    private Integer platformType;
    /**
     * 平台名称
     */
    private String platformName;
    /**
     * 品牌名称(外部平台)
     */
    private String brandNameFromOut;
    /**
     * 品牌主页(外部平台地址)
     */
    private String brandUrl;
    /**
     * 管家Id
     */
    private Integer stewardId;
    /**
     * 品牌类型 (来自house_info_type.type_no)
     */
    private Integer brandType;
    /**
     * 城市编号
     */
    private String cityCode;
    /**
     *  是否已合作, 合作：0; 未合作：1
     */
    private Byte isJoin;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;

}