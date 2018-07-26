package com.hizhu.crawler.brand.test;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/13 21:52
 */
public class Test4SplitUrl {

    public static void main(String[] args) {

//        String url = "tj.mgzf.com/list/xz2427_hedongqu?page=1";
//
//        final String[] split = url.split("//");
//        final String substring = split[split.length - 1].substring(0, 2);
//        System.out.println(substring);
//        System.out.println(split[split.length-1]);

        String startUrl = "https://m.ke.com/chuzu/sh/apartment";
        final String[] split = startUrl.split("https://m.ke.com/chuzu/");
        final String s = split[split.length - 1].substring(0,2);
        System.out.println(s);

    }
}
