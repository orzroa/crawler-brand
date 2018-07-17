package com.hizhu.crawler.brand.mapper4temp;

import com.hizhu.crawler.brand.entity.po.temp.HouseInfoType;

import java.util.List;

public interface HouseInfoTypeMapper4Temp {
    int deleteByPrimaryKey(String id);

    int insert(HouseInfoType record);

    int insertSelective(HouseInfoType record);

    HouseInfoType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HouseInfoType record);

    int updateByPrimaryKey(HouseInfoType record);

    /**
     *  ===========下面Mapper方法为手动添加 ===========
     */

    /**
     *  查询根据Object
     * @param record
     * @return
     */
    List<HouseInfoType> selectByObject(HouseInfoType record);
}