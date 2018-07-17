package com.hizhu.crawler.brand.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/4 9:53
 */
public interface LogService {

    /**
     *  获取当前类名 和 方法名
     * @return
     */
    default String getLocation() {
        StackTraceElement stackTraceElement =  Thread.currentThread().getStackTrace()[2];
        return "类名："+stackTraceElement.getClassName()
                +"，方法名："+stackTraceElement.getMethodName() + "；打印信息：";
    }
    /**
     * 获取日志对象
     * @param T
     * @return
     */
    static Logger getLogger(Class T){
        Logger logger = LoggerFactory.getLogger(T.getClass());
        return logger;
    }

}
