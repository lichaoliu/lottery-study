package com.me.lotteryapi.game.dao;

import com.me.lotteryapi.game.entity.Game;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @program: lottery-study
 * @description:
 * @author:
 * @create: 2019-07-14 15:05
 */
@Mapper
public interface GameMapper {

    Game getGameById(String id);

    Set<Game> getGamesByGameCateGoryId(String gameCateGoryId);
}
