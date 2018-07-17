package com.hizhu.crawler.brand.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * Description ：管家信息：gaodu.steward_info
 *
 * @author： manji
 * 2018/7/3 11:40
 */
@Data
public class StewardInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 管家Id
     */
    private Integer stewardId;
    /**
     * 管家姓名
     */
    private String stewardName;
    /**
     * 手机号码_ 可确定唯一性
     */
    private String cellPhone;
    /**
     * 电话号码
     */
    private String telephone;
    /**
     * 是否嗨住自己的管家 （0：是，1：不是）
     */
    private Byte isMine;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;

}