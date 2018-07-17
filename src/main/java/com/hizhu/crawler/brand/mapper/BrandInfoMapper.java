package com.hizhu.crawler.brand.mapper;

import com.hizhu.crawler.brand.entity.po.BrandInfo;

import java.util.List;

/**
 * 品牌信息Mapper
 * @author manji
 * @Time 2018年7月3日15:45:35
 */
public interface BrandInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandInfo record);

    int insertSelective(BrandInfo record);

    BrandInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandInfo record);

    int updateByPrimaryKey(BrandInfo record);

    /**
     *  ===========下面Mapper方法为手动添加 ===========
     */

    /**
     *  查询根据Object
     * @param record
     * @return
     */
    List<BrandInfo> selectByObject(BrandInfo record);



}
