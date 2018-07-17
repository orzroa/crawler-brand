package com.hizhu.crawler.brand.service.impl;

import com.hizhu.crawler.brand.entity.bo.SaveBrandInfo;
import com.hizhu.crawler.brand.entity.enums.PlatformType;
import com.hizhu.crawler.brand.service.BrandInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/4 15:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring.xml")
public class BrandInfoServiceImplTest {

    @Autowired
    BrandInfoService brandInfoService;

    @Test
    public void saveBrandInfo() {

        SaveBrandInfo saveBrandInfo = new SaveBrandInfo();
        saveBrandInfo.setBrandNameFromOut("蘑菇公寓");
        saveBrandInfo.setBrandUrl("www.baidu.com");
//        saveBrandInfo.setCityName("迈阿密");
        saveBrandInfo.setPlatformType(PlatformType.MOGO);
        saveBrandInfo.setTelephone("13988888888");
        saveBrandInfo.setCityCode("001001");
        saveBrandInfo.setStewardName("管家Test");

        brandInfoService.saveBrandInfo(saveBrandInfo);

    }
}