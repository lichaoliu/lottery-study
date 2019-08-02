package com.me.lotteryapi.dictconfig.domain;

import lombok.Data;
import org.springframework.data.annotation.Version;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-08-02 17:11
 */
@Data
public class DictItem {
    /**
     * 主键id
     */
    private String id;

    /**
     * 字典项code
     */
    private String dictItemCode;

    /**
     * 字典项名称
     */
    private String dictItemName;

    /**
     * 排序号
     */
    private Double orderNo;

    /**
     * 乐观锁版本号
     */
    @Version
    private Long version;

    /**
     * 所属字典类型id
     */
    private String dictTypeId;

    /**
     * 所属字典类型
     */
    private DictType dictType;

}
