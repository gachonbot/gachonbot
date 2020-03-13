package com.bot.gachon.controller;

import com.bot.gachon.domain.Gachon;
import com.bot.gachon.dto.response.HaksikDto;
import com.bot.gachon.dto.response.MaskDto;

import com.bot.gachon.dto.response.WeatherDto;
import com.bot.gachon.service.GachonService;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


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




