package com.oneself.common.spring;

import org.apache.catalina.User;

import java.util.HashSet;

/**
 * @Description:
 * @Title: SpringMain
 * @Author wen
 * @Date: 2022/9/12 3:34
 */
public class SpringMain {
    public static void main(String[] args) {
        HashSet<Object> set = new HashSet<>();


        UserTest p1 = new UserTest("张三","1",25);

        UserTest p2 = new UserTest("李四","2",26);

        UserTest p3 = new UserTest("王五","3",27);

        set.add(p1);

        set.add(p2);

        set.add(p3);

        System.out.println("总共有:"+set.size()+" 个元素!"); //结果：总共有:3 个元素!

        p3.setAge(2); //修改p3的年龄,此时p3元素对应的hashcode值发生改变

        set.remove(p3); //此时remove不掉，造成内存泄漏

        set.add(p3); //重新添加，可以添加成功

        System.out.println("总共有:"+set.size()+" 个元素!"); //结果：总共有:4 个元素!


    }
}
