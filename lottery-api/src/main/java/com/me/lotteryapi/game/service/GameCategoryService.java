package com.me.lotteryapi.game.service;

import com.me.lotteryapi.game.dao.GameCategoryMapper;
import com.me.lotteryapi.game.entity.GameCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: lottery-study
 * @description: 彩种类别Service
 * @author:
 * @create: 2019-07-14 16:02
 */
@Service
public class GameCategoryService {

    @Autowired
    GameCategoryMapper gameCategoryMapper;

    @Autowired
    GameService gameService;

    public GameCategory getGameCategoryById(String id){
        return gameCategoryMapper.getGameCategoryById(id);
    }

}
