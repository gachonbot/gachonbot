package com.bot.gachon.controller;


import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.*;
import com.bot.gachon.service.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class GachonController {

    private final Haksik haksik;
    private final Mask mask;
    private final Weather weather;
    private final Guide guide;
    private final Library library;
    private final Main main;
    public GachonController(Haksik haksik, Mask mask, Weather weather, Guide guide, Library library, Main main) {

        this.haksik = haksik;
        this.mask = mask;
        this.weather = weather;
        this.guide = guide;
        this.library = library;
        this.main = main;
    }

    @PostMapping("/weather")
    public WeatherResponse getWeather(@RequestBody BotRequest botRequest){
        return weather.getWeatherInfo2(botRequest);
    }

    @PostMapping("/haksik/domitory")
    public HaksikResponse getHaksikInfo_domitory(@RequestBody BotRequest botRequest)throws IOException {
        return haksik.getHaksikInfo_domitory(botRequest);
    }

    @PostMapping("/haksik/vision")
    public HaksikResponse getHaksikInfo_vision(@RequestBody BotRequest botRequest) throws IOException {
        return haksik.getHaksikInfo("vision");
    }

    @PostMapping("/haksik/art")
    public HaksikResponse getHaksikInfo_art(@RequestBody BotRequest botRequest) throws IOException {
        return haksik.getHaksikInfo("art");
    }

    @PostMapping("/haksik/edu")
    public HaksikResponse getHaksikInfo_edu(@RequestBody BotRequest botRequest) throws IOException {
        return haksik.getHaksikInfo("edu");
    }

    @PostMapping("/mask/menu")
    public MaskMenuDto getMaskInfo(@RequestBody BotRequest botRequest) {
        return mask.findMaskInfo(botRequest);
    }

    @PostMapping("/main/menu")
    public MainMenuDto getMainMenu(@RequestBody BotRequest botRequest) {
        return main.getMainmenu(botRequest);
    }


    @PostMapping("/mask/info")
    public MaskReplayResponse getMask(@RequestBody BotRequest botRequest) {
        return mask.getMaskInfo(botRequest);
    }


    @PostMapping("/yesterday/mask")
    public MaskYesterdayResponse getYesterdayInfo(@RequestBody BotRequest botRequest){
        return mask.getYesterdayInfo(botRequest);
    }

    @PostMapping("/guide/news")
    public GuideResponse guideResponse(@RequestBody BotRequest botRequest) throws IOException {
        return guide.getNoticeInfo("news");
    }
    @PostMapping("/guide/benefit")
    public GuideResponse guideResponse2(@RequestBody BotRequest botRequest) throws IOException {
        return guide.getNoticeInfo("benefit");
    }
    @PostMapping("/guide/notice")
    public GuideResponse guideResponse3(@RequestBody BotRequest botRequest) throws IOException {
        return guide.getNoticeInfo("notice");
    }

    @PostMapping("/library")
    public LibraryResponse info(@RequestBody BotRequest botRequest) throws IOException{
        return library.getInfo(botRequest);
    }

//    @PostMapping("/schedule")
//    public  ScheduleResponse scheduleResponse(@RequestBody BotRequest botRequest) throws IOException{
//        return gachonService.getScheduleInfo(botRequest);
//    }
}




