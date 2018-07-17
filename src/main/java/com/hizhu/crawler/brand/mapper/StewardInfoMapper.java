package com.hizhu.crawler.brand.mapper;

import com.hizhu.crawler.brand.entity.po.StewardInfo;

import java.util.List;

/**
 * 管家信息Mapper
 * @author manji
 * @Time 2018年7月3日15:45:35
 */
public interface StewardInfoMapper {

    int deleteByPrimaryKey(Integer stewardId);

    int insert(StewardInfo record);

    int insertSelective(StewardInfo record);

    StewardInfo selectByPrimaryKey(Integer stewardId);

    int updateByPrimaryKeySelective(StewardInfo record);

    int updateByPrimaryKey(StewardInfo record);

    /**
     *  ===========下面Mapper方法为手动添加 ===========
     */
    /**
     * 查询
     * @param stewardInfo
     * @return
     */
    List<StewardInfo> selectByObject(StewardInfo stewardInfo);


}