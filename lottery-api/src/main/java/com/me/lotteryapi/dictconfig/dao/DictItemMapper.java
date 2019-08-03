package com.me.lotteryapi.dictconfig.dao;

import com.me.lotteryapi.dictconfig.domain.DictItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictItemMapper {

    DictItem getDictItemByDictTypeId(@Param("dictTypeId") String dictTypeId,@Param("dictItemCode") String dictItemCode);
}
