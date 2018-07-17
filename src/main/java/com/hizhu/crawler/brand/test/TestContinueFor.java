package com.hizhu.crawler.brand.test;

import java.util.Arrays;
import java.util.List;

/**
 * Description ：
 *
 * @author： manji
 * 2018/7/13 14:23
 */
public class TestContinueFor {

    public static void main(String[] args) {

//        int num = 10;
//        for (int i = 0 ; i < num ; i ++){
//            if (i == 5){  continue; }
//            System.out.println(i);
//        }
        String string = "f,h,d,a,f,h,d,k,s,h,f,k,d,a,s,h";
        final String[] strings =string.split(",");
        final List<String> stringsList = Arrays.asList(strings);
        stringsList.forEach(s->{
            if("s".equals(s)){
                return;
            }
            System.out.println(s);
        });

    }
}
