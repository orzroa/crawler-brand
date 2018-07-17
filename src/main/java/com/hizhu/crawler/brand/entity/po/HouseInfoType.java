package com.hizhu.crawler.brand.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author manji
 * @date 2018年7月4日13:19:59
 */
@Data
public class HouseInfoType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;
    /**
     * 类型编号
     */
    private String typeNo;
    /**
     * 房源详细类型
     */
    private Integer infoType;
    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer indexNo;
    /**
     * 创建时间
     */
    private Integer createTime;
    /**
     * 记录状态
     */
    private Integer recordStatus;
    /**
     * 显示
     */
    private Integer isDisplay;
    /**
     * 城市Id
     */
    private String cityCode;
    /**
     * 资源类型 0 : 整租, 1 : 合租
     */
    private Integer resourceType;

}