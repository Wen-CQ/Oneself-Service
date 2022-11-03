package com.oneself.common.utils;

import java.util.*;

/**
 * @Description:
 * @Title: NumberUtils
 * @Author wen
 * @Date: 2022/8/20 18:38
 */
public class NumberUtils {


    public static List<Integer> randNumberArray(Integer num,Integer count){
        Set<Integer> array = new HashSet<>();
        Random random = new Random();
        int i=0;
        while (array.size()<num){
            int index = random.nextInt(count);
            array.add(index);
            i++;
            if (i>1000){
                break;
            }
        }
        if (i>1000){
            array.add(0);
        }
        return new ArrayList<>(array);
    }
 }
