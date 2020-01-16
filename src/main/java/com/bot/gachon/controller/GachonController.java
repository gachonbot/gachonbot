package com.bot.gachon.controller;

import com.bot.gachon.dto.response.SampleResponse;
import com.bot.gachon.service.GachonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class GachonController {

    private final GachonService gachonService;

    public GachonController(GachonService gachonService) {this.gachonService = gachonService;}

    @GetMapping("/sample")
    public SampleResponse doSample(@RequestParam("userId") String userId) {
        return gachonService.doSample(userId);
    }
}
