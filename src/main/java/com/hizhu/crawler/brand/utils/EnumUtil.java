package com.hizhu.crawler.brand.utils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Description ：枚举工具类
 *
 * @author： manji
 * 2018/7/11 10:30
 */
public class EnumUtil {

    /**
     * 根据城市短名字 获取枚举对象
     * @param clz
     * @param cityShortName
     * @param <E>
     * @return
     */
    public static <E> E getByCityShortName(Class<E> clz, String cityShortName) {
        try {
            for (E e : clz.getEnumConstants()) {
                Field field = clz.getDeclaredField("cityShortName");
                field.setAccessible(true);
                if (Objects.equals(field.get(e), cityShortName)) {
                    return e;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("根据cityShortName获取枚举实例异常" + clz + " cityShortName:" + cityShortName, e);
        }
        return null;
    }

}
