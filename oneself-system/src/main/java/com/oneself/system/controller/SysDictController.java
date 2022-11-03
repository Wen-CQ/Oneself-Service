package com.oneself.system.controller;




import com.oneself.system.service.SysDictService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * (SysDict)表控制层
 *
 * @author makejava
 * @since 2022-07-28 19:30:49
 */
@RestController
@RequestMapping("sysDict")
public class SysDictController{
    /**
     * 服务对象
     */
    @Autowired
    private SysDictService sysDictService;

}

