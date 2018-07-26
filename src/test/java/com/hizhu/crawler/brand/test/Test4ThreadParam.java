package com.hizhu.crawler.brand.test;

import com.hizhu.crawler.brand.entity.enums.PlatformType;
import lombok.Setter;

import java.util.HashMap;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/10 17:13
 */
public class Test4ThreadParam implements Runnable {

    @Setter
    private HashMap<String,String> map;

    @Override
    public void run() {
        final String threadName = map.get("threadName");
        final String cityShortName = map.get("cityShortName");
        final String platformName = map.get("platformName");
        System.out.println( threadName+ "====" + cityShortName +  "====" +  platformName);
    }


    public static void main(String[] args) {
        Test4ThreadParam test4Thread = new Test4ThreadParam();
        HashMap<String,String> param = new HashMap<>(32);
        param.put("threadName","SHANGHAI");
        param.put("cityShortName","sh");
        param.put("platformName", PlatformType.BEIKE.getSpell());
        test4Thread.setMap(param);

        Thread thread = new Thread(test4Thread);
        thread.start();
    }
}
