package com.me.lotteryapi.dictconfig.service;

import com.me.lotteryapi.dictconfig.dao.DictTypeMapper;
import com.me.lotteryapi.dictconfig.domain.DictType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-08-02 17:27
 */
@Service
public class DictTypeService {

    @Autowired
    DictTypeMapper dictTypeMapper;

    public DictType getDictTypeByCode(String dictTypeCode) {
        DictType dictType = dictTypeMapper.getDictTypeByCode(dictTypeCode);
        return dictType;
    }
}
