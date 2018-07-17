package com.hizhu.crawler.brand.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description ：平台类型
 *
 * @author： manji
 * 2018/7/3 10:39
 */
@Getter
@AllArgsConstructor
public enum PlatformType {
    /**
     * 贝壳
     */
    BEIKE(0,"贝壳","BEIKE"),
    /**
     * 蘑菇
     */
    MOGO(1,"蘑菇","MOGO");
    private int value;
    private String name;
    private String spell;
}
