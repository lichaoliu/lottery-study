package com.me.lotteryapi.game.service;

import com.me.lotteryapi.game.dao.GameMapper;
import com.me.lotteryapi.game.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @program: lottery-study
 * @description: 彩种Service
 * @author:
 * @create: 2019-07-14 15:05
 */
@Service
public class GameService {

    @Autowired
    GameMapper gameMapper;

    public Game getGameById(String id) {
        return gameMapper.getGameById(id);
    }

}
