package com.bot.gachon.controller;


import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.*;
import com.bot.gachon.service.GachonService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        return gachonService.getHaksikInfo(building);
    }

    @PostMapping("/mask/menu")
    public MaskMenuDto getMaskInfo(@RequestBody BotRequest botRequest) {
        return gachonService.findMaskInfo(botRequest);
    }

    @PostMapping("/main/menu")
    public MainMenuDto getMainMenu(@RequestBody BotRequest botRequest) {
        return gachonService.getMainmenu(botRequest);
    }


    @PostMapping("/mask/info")
    public MaskReplayResponse getMask(@RequestBody BotRequest botRequest) {
        return gachonService.getMaskInfo(botRequest);
    }




    @PostMapping("/yesterday/mask")
    public MaskYesterdayResponse getYesterdayInfo(@RequestBody BotRequest botRequest){
        return gachonService.getYesterdayInfo(botRequest);
    }

    @PostMapping("/guide/news")
    public GuideResponse guideResponse(@RequestBody BotRequest botRequest) throws IOException {
        return gachonService.getNoticeInfo("news");
    }
    @PostMapping("/guide/benefit")
    public GuideResponse guideResponse2(@RequestBody BotRequest botRequest) throws IOException {
        return gachonService.getNoticeInfo("benefit");
    }
    @PostMapping("/guide/notice")
    public GuideResponse guideResponse3(@RequestBody BotRequest botRequest) throws IOException {
        return gachonService.getNoticeInfo("notice");
    }

    @PostMapping("/library")
    public LibraryResponse info(@RequestBody BotRequest botRequest) throws IOException{
        return gachonService.getInfo(botRequest);
    }

}




