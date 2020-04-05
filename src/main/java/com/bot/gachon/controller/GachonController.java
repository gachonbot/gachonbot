package com.bot.gachon.controller;

import com.bot.gachon.domain.GachonMask;
import com.bot.gachon.domain.GachonYesterdayMask;
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
        return gachonService.findWeatherInfo();

    }
    @GetMapping("/mask")
    public MaskMenuDto getMaskInfo() {
        return gachonService.findMaskInfo2();

    }

    @GetMapping("/haksik/building")
    public HaksikDto getHaksikInfo(@RequestParam("building") String building) throws IOException {
        return gachonService.findHaksikInfo(building);
    }



    @PostMapping("/mask/menu")
    public MaskMenuDto getMaskInfo(@RequestBody BotRequest botRequest) {
    return gachonService.findMaskInfo(botRequest);
    }

    @PostMapping("/mask/info")
    public MaskReplayResponse getMask(@RequestBody BotRequest botRequest) {
        return gachonService.replayResponse(botRequest);
    }

     @GetMapping("/remain/mask")
    public List<GachonMask> getRemainMaskInfo() {
        return gachonService.getRemainMaskInfo();
    }

    @GetMapping("/yesterday/mask")
    public  List<GachonYesterdayMask> getYesterdatInfo(){ return gachonService.findYesterdayMaskInfo();
    }
    @PostMapping("/yesterday/mask")
    public MaskYesterdayResponse getYesterdayInfo(@RequestBody BotRequest botRequest){
        return gachonService.findYesterdayInfo(botRequest);
    }

    @GetMapping("/dust")
    public DustModel getDust() throws IOException {
        return gachonService.findDust();
    }




}




