package com.hizhu.crawler.brand.mapper;

import com.hizhu.crawler.brand.entity.po.StewardInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/4 13:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring.xml")
public class StewardInfoTest{

    @Autowired
    StewardInfoMapper stewardInfoMapper;

    @Test
    public void testInsertReturnKey(){
        StewardInfo stewardInfo = new StewardInfo();

        stewardInfo.setStewardName("管家Test-springBoot");
        stewardInfo.setTelephone("13988888888");

        stewardInfoMapper.insert(stewardInfo);
        System.out.println(stewardInfo.getStewardId());
    }
}
