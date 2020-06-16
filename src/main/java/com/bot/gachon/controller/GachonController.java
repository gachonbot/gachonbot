package com.bot.gachon.controller;


import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.*;
import com.bot.gachon.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
@RestController
public class GachonController {

    private final HaksikService haksikService;
    private final MaskService maskService;
    private final WeatherService weatherService;
    private final GuideService guideService;
    private final LibraryService libraryService;
    private final MainService mainService;
    private final ScheduleService scheduleService;

    @Autowired
    private Environment env;

    @PostMapping("/profile")
    public String getProfile(){
        String[] profiles = env.getActiveProfiles();
        String profile = profiles.length > 1 ? profiles[1] : profiles[0] == null ? "" : profiles[0];
        return profile;
    }

    public GachonController(HaksikService haksikService, MaskService maskService, WeatherService weatherService, GuideService guideService, LibraryService libraryService, MainService mainService,
                            ScheduleService scheduleService) {

        this.haksikService = haksikService;
        this.maskService = maskService;
        this.weatherService = weatherService;
        this.guideService = guideService;
        this.libraryService = libraryService;
        this.mainService = mainService;
        this.scheduleService = scheduleService;
    }

    @PostMapping("/weather")
    public WeatherResponse getWeather(@RequestBody BotRequest botRequest) {
        return weatherService.getWeatherInfo2(botRequest);
    }

    @PostMapping("/haksik/domitory")
    public HaksikResponse getHaksikInfo_domitory(@RequestBody BotRequest botRequest) throws IOException {
        return haksikService.getHaksikInfo_domitory(botRequest);
    }

    @PostMapping("/haksik/vision")
    public HaksikResponse getHaksikInfo_vision(@RequestBody BotRequest botRequest) throws IOException {
        return haksikService.getHaksikInfo("vision");
    }

    @PostMapping("/haksik/art")
    public HaksikResponse getHaksikInfo_art(@RequestBody BotRequest botRequest) throws IOException {
        return haksikService.getHaksikInfo("art");
    }

    @PostMapping("/haksik/edu")
    public HaksikResponse getHaksikInfo_edu(@RequestBody BotRequest botRequest) throws IOException {
        return haksikService.getHaksikInfo("edu");
    }

    @PostMapping("/mask/menu")
    public MaskMenuDto getMaskInfo(@RequestBody BotRequest botRequest) {
        return maskService.findMaskInfo(botRequest);
    }

    @PostMapping("/main/menu")
    public MainMenuDto getMainMenu(@RequestBody BotRequest botRequest) {
        return mainService.getMainmenu(botRequest);
    }


    @PostMapping("/mask/info")
    public MaskReplayResponse getMask(@RequestBody BotRequest botRequest) {
        return maskService.getMaskInfo(botRequest);
    }


    @PostMapping("/yesterday/mask")
    public MaskYesterdayResponse getYesterdayInfo(@RequestBody BotRequest botRequest) {
        return maskService.getYesterdayInfo(botRequest);
    }

    @PostMapping("/guide/news")
    public GuideResponse guideResponse(@RequestBody BotRequest botRequest) throws IOException {
        return guideService.getNoticeInfo("news");
    }

    @PostMapping("/guide/benefit")
    public GuideResponse guideResponse2(@RequestBody BotRequest botRequest) throws IOException {
        return guideService.getNoticeInfo("benefit");
    }

    @PostMapping("/guide/notice")
    public GuideResponse guideResponse3(@RequestBody BotRequest botRequest) throws IOException {
        return guideService.getNoticeInfo("notice");
    }

    @PostMapping("/library")
    public LibraryResponse info(@RequestBody BotRequest botRequest) throws IOException {
        return libraryService.getInfo(botRequest);
    }

    @PostMapping("/schedule")
    public ScheduleReponse scheduleResponse(@RequestBody BotRequest botRequest) throws IOException {
        return scheduleService.getSchedulInfo(botRequest);
    }
    @PostMapping("/schedule/month")
    public ScheudleMonthMenu scheudleMonthMenu(@RequestBody BotRequest botRequest) throws IOException {
        return scheduleService.getSchedulMenu(botRequest);
    }

    @PostMapping("/schedule/month2")
    public ScheudleMonthMenu_D scheudleMonthMenu_d(@RequestBody BotRequest botRequest) throws IOException {
        return scheduleService.getSchedulMenu2(botRequest);
    }

}




