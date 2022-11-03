package com.oneself.common.enums;

/**
 * @Description:
 * @Title: LockTypeEnum
 * @Author wen
 * @Date: 2022/8/30 1:42
 */
public enum LockTypeEnum {

    REENTRANT_LOCK("reentrant_lock","可重入锁"),
    NO_REENTRANT_LOCK("no_reentrant_lock","可重入锁");

    private String code;

    private String desc;

    LockTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
