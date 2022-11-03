package com.oneself.design.structure.adapter;

/**
 * @Description: 适配器模式
 *将一个类的接口适配成用户所期待的。一个适配允许通常因为接口不兼容而不能在一起工作的类工作在一起，
 * 做法是将类自己的接口包裹在一个已存在的类中。
 * @Title: WorkAdapter
 * @Author wen
 * @Date: 2022/9/11 3:39
 */
public class WorkAdapter implements IWorkAdapter{

    @Override
    public void work(Object worker) {
        if (worker instanceof WorkScore){
            ((WorkScore) worker).compute();
        }
        if (worker instanceof WorkMoney){
            ((WorkMoney) worker).getMoney();
        }
    }


}
