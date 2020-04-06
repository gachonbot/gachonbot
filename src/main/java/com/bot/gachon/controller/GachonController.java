package com.bot.gachon.controller;

import com.bot.gachon.domain.GachonMask;
import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.*;
import com.bot.gachon.service.GachonService;
import org.springframework.web.bind.annotation.*;

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
        return gachonService.getWeatherInfo();

    }

    @GetMapping("/haksik/building")
    public HaksikDto getHaksikInfo(@RequestParam("building") String building) throws IOException {
        return gachonService.getHaksikInfo(building);
    }



    @PostMapping("/mask/menu")
    public MaskMenuDto getMaskInfo(@RequestBody BotRequest botRequest) {
    return gachonService.findMaskInfo(botRequest);
    }

    @PostMapping("/mask/info")
    public MaskReplayResponse getMask(@RequestBody BotRequest botRequest) {
        return gachonService.getMaskInfo(botRequest);
    }


    @PostMapping("/yesterday/mask")
    public MaskYesterdayResponse getYesterdayInfo(@RequestBody BotRequest botRequest){
        return gachonService.getYesterdayInfo(botRequest);
    }

    @GetMapping("/dust")
    public DustModel getDust() throws IOException {
        return gachonService.findDust();
    }




}




