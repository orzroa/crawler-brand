package com.hizhu.crawler.brand.test;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/10 16:18
 */
public class TestReplace {

    public static void main(String[] args) {

        String string = "//wanliuguojib.mgzf.com/bj";
        final String s = string.replaceAll("//", "");
        System.out.println(s);
    }
}
