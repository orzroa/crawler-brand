package com.hizhu.crawler.brand.mapper;

import com.hizhu.crawler.brand.entity.po.HouseInfoType;

import java.util.List;

/**
 * 房屋信息类型 Mapper
 * @author manji
 * @date  2018年7月4日13:18:32
 */
public interface HouseInfoTypeMapper {

    /**
     * 根据主键del
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入 返回Id
     * @param record
     * @return
     */
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