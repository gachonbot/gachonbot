package com.bot.gachon.service;

import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.MainMenuDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MainService {


    public MainMenuDto getMainmenu(BotRequest botRequest) {

        return MainMenuDto.builder().build();
    }



}
