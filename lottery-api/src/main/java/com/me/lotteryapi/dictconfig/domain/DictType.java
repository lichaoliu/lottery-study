package com.me.lotteryapi.dictconfig.domain;

import lombok.Data;

/**
 * @program: lottery-study
 * @description: 字典类型
 * @author:
 * @create: 2019-08-01 17:34
 */
@Data
public class DictType {
    /**
     * 主键id
     */
    private String id;

    /**
     * 字典类型code
     */
    private String dictTypeCode;

    /**
     * 字典类型名称
     */
    private String dictTypeName;

    /**
     * 备注
     */
    private String note;

    /**
     * 乐观锁版本号
     */
    private Long version;
}
