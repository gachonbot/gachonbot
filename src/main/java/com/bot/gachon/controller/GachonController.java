package com.bot.gachon.controller;

import com.bot.gachon.domain.GachonMask;
import com.bot.gachon.domain.GachonYesterdayMask;
import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.DustModel;
import com.bot.gachon.dto.res.HaksikDto;
import com.bot.gachon.dto.res.TextReplyResponse;
import com.bot.gachon.dto.res.WeatherDto;
import com.bot.gachon.service.GachonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GachonController {

    private final GachonService gachonService;

    public GachonController(GachonService gachonService) {

        this.gachonService = gachonService;

    }

    @GetMapping("/weather")
    public WeatherDto getWeatherInfo() throws Exception {
        return gachonService.findWeatherInfo();

    }

    @GetMapping("/haksik/building")
    public HaksikDto getHaksikInfo(@RequestParam("building") String building) throws IOException {
        return gachonService.findHaksikInfo(building);
    }


//    public List<GachonMask> getMaskInfo() {
//        return gachonService.findMaskInfo();
//    }
    @GetMapping("/mask")
    public TextReplyResponse getMaskInfo(@RequestBody BotRequest botRequest) {
        return gachonService.findMaskInfo(botRequest);
    }

    @GetMapping("/remain/mask")
    public List<GachonMask> getRemainMaskInfo() {
        return gachonService.getRemainMaskInfo();
    }

    @GetMapping("/yesterday/mask")
    public  List<GachonYesterdayMask> getYesterdatInfo(){ return gachonService.findYesterdayMaskInfo();
    }

    @GetMapping("/dust")
    public DustModel getDust() throws IOException {
        return gachonService.findDust();
    }




}




