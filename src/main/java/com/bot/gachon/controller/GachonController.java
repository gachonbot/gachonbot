package com.bot.gachon.controller;

import com.bot.gachon.dto.response.HaksikDto;
import com.bot.gachon.dto.response.WeatherDto;
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

    @RequestMapping("/haksik/building")
    public HaksikDto getHaksikInfo(@RequestParam("building") String building) throws IOException {
         return gachonService.findHaksikInfo(building);
    }
}



