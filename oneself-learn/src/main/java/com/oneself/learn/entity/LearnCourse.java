package com.oneself.learn.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 科目表(LearnCourse)实体类
 *
 * @author makejava
 * @since 2022-08-09 23:19:28
 */
@Data
public class LearnCourse implements Serializable {
    private static final long serialVersionUID = -75463530358593869L;
    /**
     * 科目ID
     */
    private Long courseId;
    /**
     * 科目名称
     */
    private String courseName;
    /**
     * 状态（0正常 1暂停）
     */
    private String status;
    /**
     * 标签
     */
    private String tag;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注信息
     */
    private String remark;

}

