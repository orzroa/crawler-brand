package com.hizhu.crawler.brand.mapper;

import com.hizhu.crawler.brand.entity.enums.PlatformType;
import com.hizhu.crawler.brand.entity.po.BrandInfo;
import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/3 14:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring.xml")
public class BrandInfoMapperTest {

    @Autowired
    private BrandInfoMapper brandInfoMapper;

    @Test
    public void testInsert(){
        BrandInfo brandInfo  =  new BrandInfo();
        brandInfo.setId(00000);
        brandInfo.setBrandNameFromOut("OutName");
        brandInfo.setBrandType(1601);
        brandInfo.setPlatformType(PlatformType.BEIKE.getValue());
        brandInfo.setStewardId(1601);
        brandInfo.setIsJoin(NumberUtils.BYTE_ZERO);

        brandInfoMapper.insert(brandInfo);
    }









}
