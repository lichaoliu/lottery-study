package com.me.lotteryapi.dictconfig.service;

import com.alicp.jetcache.anno.Cached;
import com.me.lotteryapi.dictconfig.dao.DictItemMapper;
import com.me.lotteryapi.dictconfig.domain.DictItem;
import com.me.lotteryapi.dictconfig.domain.DictType;
import com.me.lotteryapi.dictconfig.vo.DictItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.TimeUnit;

/**
 * @program: lottery-study
 * @description: 字典表查询service
 * @author:
 * @create: 2019-08-02 13:40
 */
@Service
public class DictItemService {

    @Autowired
    DictTypeService dictTypeService;

    @Autowired
    DictItemMapper dictItemMapper;

    @Cached(name = "dictItem_", key = "#dictTypeCode + '_' +  #dictItemCode", expire = 3600, timeUnit = TimeUnit.SECONDS)
    @Transactional(readOnly = true)
    public DictItemVO getDictItemByDictCodeAndId(String dictTypeCode, String dictItemCode) {
        DictType dictType = dictTypeService.getDictTypeByCode(dictTypeCode);
        DictItem dictItem = getDictItemByDictTypeId(dictType.getId(), dictItemCode);
        DictItemVO dictItemVO = DictItemVO.convertFor(dictItem);
        return dictItemVO;
    }

    public DictItem getDictItemByDictTypeId(String dictTypeId, String dictItemCode) {
        return dictItemMapper.getDictItemByDictTypeId(dictTypeId, dictItemCode);
    }
}
