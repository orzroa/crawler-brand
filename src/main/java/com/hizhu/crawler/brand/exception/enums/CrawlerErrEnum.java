package com.hizhu.crawler.brand.exception.enums;

/**
 * Description ：抓取模块错误 类型枚举
 *
 * @author： manji
 * 2018/7/4 10:36
 */
public enum CrawlerErrEnum {

    QUERY_REQ_ERR(MagicError.QUERY_1001,"查询请求参数有误！"),
    QUERY_RES_ERR(MagicError.QUERY_1002,"查询响应结果为空！"),
    UPDATE_REQ_ERR(MagicError.UPDATE_1001,"更新数据参数有误！"),
    UPDATE_RES_ERR(MagicError.UPDATE_1002,"更新数据失败！"),
    ADD_REQ_ERR(MagicError.ADD_1001,"新增请求参数有误！"),
    ADD_RES_ERR(MagicError.ADD_1002,"新增数据失败！"),
    DEL_RES_ERR(MagicError.DEL_1002,"新增数据失败！"),;

    private int code;
    private String info;

    public int getCode() {
        return code;
    }
    public String getInfo() {
        return info;
    }

    CrawlerErrEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }
}
