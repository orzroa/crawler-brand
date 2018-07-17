package com.hizhu.crawler.brand.service.impl;

import com.hizhu.crawler.brand.entity.bo.SaveBrandInfo;
import com.hizhu.crawler.brand.entity.enums.MagicNum;
import com.hizhu.crawler.brand.entity.po.BrandInfo;
import com.hizhu.crawler.brand.entity.po.temp.HouseInfoType;
import com.hizhu.crawler.brand.entity.po.StewardInfo;
import com.hizhu.crawler.brand.exception.CrawlerServerException;
import com.hizhu.crawler.brand.exception.enums.CrawlerErrEnum;
import com.hizhu.crawler.brand.mapper.BrandInfoMapper;
import com.hizhu.crawler.brand.mapper4temp.HouseInfoTypeMapper4Temp;
import com.hizhu.crawler.brand.mapper.StewardInfoMapper;
import com.hizhu.crawler.brand.service.BrandInfoService;
import com.hizhu.crawler.brand.utils.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description ：品牌信息服务
 * @author： manji
 * 2018/7/3 16:05
 */
@Slf4j
@Service("brandInfoService")
public class BrandInfoServiceImpl implements LogService,BrandInfoService {

    @Autowired
    BrandInfoMapper brandInfoMapper;
    @Autowired
    StewardInfoMapper stewardInfoMapper;
    @Autowired
    HouseInfoTypeMapper4Temp houseInfoTypeMapper4Temp;

    /**
     * 插入标记_管家
     */
    private Integer flag4Insert;

    @Override
    public void saveBrandInfo(SaveBrandInfo saveBrandInfo) {

        // 如果已经有了, 就直接返回 { 根据平台名称 & 品牌名称 &城市编码确定 品牌的唯一性 }
        BrandInfo queryParam = new BrandInfo();
        queryParam.setBrandNameFromOut(saveBrandInfo.getBrandNameFromOut());
        queryParam.setCityCode(saveBrandInfo.getCityCode());
        queryParam.setPlatformType(saveBrandInfo.getPlatformType().getValue());

        final List<BrandInfo> brandInfos = brandInfoMapper.selectByObject(queryParam);
        if (!CollectionUtils.isEmpty(brandInfos)){
            log.info(" >>>>> ===== <<<<< MMP 又是一条解析过的, 跑全量真伤不起===== " + saveBrandInfo.getBrandNameFromOut() + ",城市Code >>>>>>" + saveBrandInfo.getCityCode() );
            return;
        }

        BrandInfo insertParam = new BrandInfo();
        /**
         * 管家相关操作
         */
        //先去查, 为Null直接插入, 返回ID | 不为空拿到返回对象ID
        final String stewardName = saveBrandInfo.getStewardName();
        final String telephone = saveBrandInfo.getTelephone();

        final StewardInfo stewardInfo = this.getSteward4Brand(stewardName, telephone);
        if (stewardInfo == null){
            /**
             * 直接插入 steward 返回ID
             */
            flag4Insert = this.saveSteward(stewardName, telephone);
            if ( flag4Insert <= 0 ) {
                log.error("====>>" + getLocation() + "insert" + saveBrandInfo.getBrandNameFromOut() + "品牌管家信息失败");
                //非对外接口,异常抛出
                throw new CrawlerServerException(CrawlerErrEnum.ADD_RES_ERR.getCode(), CrawlerErrEnum.ADD_RES_ERR.getInfo());
            }
        }else {
            flag4Insert = stewardInfo.getStewardId();
        }

        //管家Id
        insertParam.setStewardId(flag4Insert);
        /**
         *  品牌相关处理
         */
        final String brandNameFromOut = saveBrandInfo.getBrandNameFromOut();
        final String cityCode = saveBrandInfo.getCityCode();

        final HouseInfoType brandInfoFromMine = this.getBrandInfoFromHouseInfoType(brandNameFromOut, cityCode);
        //已经有的  使用参数 isJoin | brandType
        if (brandInfoFromMine != null){
            // 是否加入 默认为 1 : 没加入
            insertParam.setIsJoin(NumberUtils.BYTE_ZERO);
            // 将Type_no 转成int 类型 以后重新建表的时候 品牌类型Id 使用Integer类型 自己维护 不能自动生成无序串
            insertParam.setBrandType(Integer.parseInt(brandInfoFromMine.getTypeNo()));
        }
        /**
         *  封装存储参数
         */
        insertParam.setPlatformType(saveBrandInfo.getPlatformType().getValue());
        insertParam.setPlatformName(saveBrandInfo.getPlatformType().getName());
        insertParam.setBrandNameFromOut(saveBrandInfo.getBrandNameFromOut());
        insertParam.setBrandUrl(saveBrandInfo.getBrandUrl());
        insertParam.setCityCode(saveBrandInfo.getCityCode());
        insertParam.setCreateTime(System.currentTimeMillis());

        final int i = brandInfoMapper.insertSelective(insertParam);
        log.info("信息插入+ 1  ====>>>>" + i + "<<<<===== 城市编号 >>>> " +saveBrandInfo.getCityCode());
        if (i <= 0){
            log.error("====>>" + getLocation() + "insert" + saveBrandInfo.getBrandNameFromOut() + "品牌总信息失败。" );
            //非对外接口,异常抛出
            throw new CrawlerServerException(CrawlerErrEnum.ADD_RES_ERR.getCode(), CrawlerErrEnum.ADD_RES_ERR.getInfo());
        }

    }

    /**
     * 获取管家信息
     * @param stewardName- 管家名称
     * @param telephone - 管家电话号码
     * @return 管家 Object
     */
    private  StewardInfo getSteward4Brand(String stewardName,String telephone){

        StewardInfo stewardCondition = new StewardInfo();

        stewardCondition.setStewardName(stewardName);
        stewardCondition.setTelephone(telephone);

        //根据姓名和电话查询管家信息
        final List<StewardInfo> stewardInfoList = stewardInfoMapper.selectByObject(stewardCondition);
        if (CollectionUtils.isEmpty(stewardInfoList)){
            return null;
        }
        return stewardInfoList.get(NumberUtils.INTEGER_ZERO);
    }

    /**
     * 保存管家信息 From 爬取
     * @param stewardName 管家名字
     * @param telephone 管家电话
     * @return id 插入数据生成的Id
     */
    private int saveSteward(String stewardName,String telephone ){
        StewardInfo stewardInfo = new StewardInfo();
        stewardInfo.setStewardName(stewardName);
        stewardInfo.setTelephone(telephone);
        //默认 不是合作 1
        stewardInfo.setIsMine(NumberUtils.BYTE_ONE);
        stewardInfo.setCreateTime(System.currentTimeMillis());
        final int i = stewardInfoMapper.insert(stewardInfo);
        //插入失败 返回0
        if (i == 0){
            return 0;
        }
        return stewardInfo.getStewardId();
    }

    /**
     * 根据品牌名称,去我们自己的库中查询 是否有此品牌信息
     * @param brandName 品牌名称
     * @param cityCode 城市编码(非必须)
     * @return
     */
    private HouseInfoType getBrandInfoFromHouseInfoType(String brandName, String cityCode){

        HouseInfoType houseInfoType = new HouseInfoType();
        // 16在 houseInfoType 表中代表 品牌 此处为固定参数
        houseInfoType.setInfoType(MagicNum.N_16);
        houseInfoType.setName(brandName);
        //非必传参数
        houseInfoType.setCityCode(cityCode);
        final List<HouseInfoType> houseInfoTypeList = houseInfoTypeMapper4Temp.selectByObject(houseInfoType);
        if (CollectionUtils.isEmpty(houseInfoTypeList)){
            return null;
        }
        return houseInfoTypeList.get(NumberUtils.INTEGER_ZERO);
    }


}
