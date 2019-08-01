package com.me.lotteryapi.dictconfig.vo;

import lombok.Data;

/**
 * @program: lottery-study
 * @description: 字典类型
 * @author:
 * @create: 2019-08-01 17:28
 */
@Data
public class DictTypeVO {
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

}
