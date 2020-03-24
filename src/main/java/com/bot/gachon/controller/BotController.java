package com.bot.gachon.controller;

import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.TextReplyResponse;
import com.bot.gachon.service.BotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {

    private final BotService botService;

    public BotController(BotService botService) {this.botService = botService;}

    @PostMapping("/echo")
    public TextReplyResponse doEcho(@RequestBody BotRequest botRequest){
        return botService.echo(botRequest);
    }
}
