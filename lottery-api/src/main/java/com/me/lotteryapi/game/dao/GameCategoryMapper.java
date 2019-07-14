package com.me.lotteryapi.game.dao;

import com.me.lotteryapi.game.entity.GameCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameCategoryMapper {
    GameCategory getGameCategoryById(String id);
}
