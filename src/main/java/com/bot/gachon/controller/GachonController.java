package com.bot.gachon.controller;

import com.bot.gachon.dto.response.DustModel;
import com.bot.gachon.dto.response.DustRepository;
import com.bot.gachon.dto.response.HaksikDto;
import com.bot.gachon.dto.response.WeatherDto;
import com.bot.gachon.service.GachonService;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class GachonController {


    private final GachonService gachonService;
    private final DustRepository dustRepository;

    public GachonController(GachonService gachonService, DustRepository dustRepository) {
        this.gachonService = gachonService;
        this.dustRepository = dustRepository;
    }


    @GetMapping("/weather")
    public WeatherDto getWeatherInfo() throws Exception {
        return gachonService.findWeatherInfo();

    }

    @GetMapping("/haksik")
    public HaksikDto getHaksikInfo() throws IOException {
         return gachonService.findHaksikInfo();
    }

    @GetMapping("/dust")
    public DustModel getDust() throws IOException {
        dustRepository.save
        return gachonService.findDust();
    }

}



