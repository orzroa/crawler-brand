package com.hizhu.crawler.brand.test;

import com.hizhu.crawler.brand.utils.extract.MogoBrandUtils;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description ：测试json
 *
 * @author： manji
 * 2018/7/13 20:06
 */
public class Test4Json {

    private static final String startUrl = "http://tj.mgzf.com/list/xz2427_hedongqu?page=1";

    public static void main(String[] args) {
        CopyOnWriteArraySet<String> list = new CopyOnWriteArraySet<>();
        list.add(startUrl);
        final CopyOnWriteArraySet<String> allDetailUrlList = MogoBrandUtils.getAllDetailUrl(list);
        allDetailUrlList.forEach(allDetailUrl->{
            System.out.println(allDetailUrl);
        });

    }
}
