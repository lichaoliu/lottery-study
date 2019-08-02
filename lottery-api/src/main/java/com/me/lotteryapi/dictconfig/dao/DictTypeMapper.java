package com.me.lotteryapi.dictconfig.dao;

import com.me.lotteryapi.dictconfig.domain.DictType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictTypeMapper {

    DictType getDictTypeByCode(String dictTypeCode);
}
