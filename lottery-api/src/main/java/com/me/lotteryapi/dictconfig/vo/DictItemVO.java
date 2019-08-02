package com.me.lotteryapi.dictconfig.vo;

import cn.hutool.core.collection.CollectionUtil;
import com.me.lotteryapi.dictconfig.domain.DictItem;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-08-02 17:10
 */
@Data
public class DictItemVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

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

    public static List<DictItemVO> convertFor(List<DictItem> dictItems) {
        if (CollectionUtil.isEmpty(dictItems)) {
            return new ArrayList<>();
        }
        List<DictItemVO> vos = new ArrayList<>();
        for (DictItem dictItem : dictItems) {
            vos.add(convertFor(dictItem));
        }
        return vos;
    }

    public static DictItemVO convertFor(DictItem dictItem) {
        if (dictItem == null) {
            return null;
        }
        DictItemVO vo = new DictItemVO();
        BeanUtils.copyProperties(dictItem, vo);
        return vo;
    }

}