package com.bot.gachon.controller;

import com.bot.gachon.dto.response.HaksikDto;
import com.bot.gachon.dto.response.MaskDto;
import com.bot.gachon.dto.response.WeatherDto;
import com.bot.gachon.service.GachonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class GachonController {


    private final GachonService gachonService;

    public GachonController(GachonService gachonService){

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

    @GetMapping("/mask")
    public MaskDto getMaskInfo() throws  Exception{
        return gachonService.findMaskInfo();
    }

}




