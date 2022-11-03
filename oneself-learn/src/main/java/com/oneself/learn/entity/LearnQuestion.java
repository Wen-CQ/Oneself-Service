package com.oneself.learn.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 题目表(LearnQuestion)实体类
 *
 * @author makejava
 * @since 2022-08-09 23:20:14
 */
@Data
public class LearnQuestion implements Serializable {
    private static final long serialVersionUID = -79538501301672282L;
    /**
     * 题目ID
     */
    private Long questionId;
    /**
     * 题目名称
     */
    private String questionName;
    /**
     * 科目ID
     */
    private Long courseId;
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

