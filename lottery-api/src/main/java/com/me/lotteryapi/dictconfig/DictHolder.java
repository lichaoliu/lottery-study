package com.me.lotteryapi.dictconfig;

import com.me.lotteryapi.dictconfig.service.DictItemService;
import com.me.lotteryapi.dictconfig.vo.DictItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: lottery-study
 * @description: 字典表操作者
 * @author:
 * @create: 2019-08-02 13:42
 */
@Component
public class DictHolder {

    @Autowired
    private DictItemService dictService;

    private static DictHolder dictHolder;

    @PostConstruct
    public void init(){
        dictHolder = this;
    }

    public static String getDictItemName(String dictTypeCode, String dictItemCode){
        DictItemVO dictItemVO = dictHolder.dictService.getDictItemByDictCodeAndId(dictTypeCode,dictItemCode);
        return "";
    }

}
