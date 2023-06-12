package com.liupanshui.msmservice.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();
    private static final DecimalFormat fourdf = new DecimalFormat("0000");
    private static final DecimalFormat sixdf = new DecimalFormat("000000");

    public static String getFourBitRandom(){
        return fourdf.format(random.nextInt(10000));
    }
    public static String getSixBitRandom(){
        return sixdf.format(random.nextInt(1000000));
    }
    /**
     *给定数据，抽取n个数据
     */
    public static ArrayList getRandom(List list,int n){
        Random random = new Random();
        HashMap<Object,Object> hashMap = new HashMap<>();
        //生成随机数字并存放到HashMap中
        for (int i = 0; i < list.size(); i++) {
            int numder = random.nextInt();
            hashMap.put(numder,i);
        }
        //从hashMap导入数据
        Object[] rojbs = hashMap.values().toArray();

        ArrayList r = new ArrayList();
        //遍历数据并打印
        for (int i = 0; i < n; i++) {
            r.add(list.get((int) rojbs[i]));
            System.out.println(list.get((int) rojbs[i]) + "\t");
        }
        System.out.println("\n");
        return r;
    }
}
