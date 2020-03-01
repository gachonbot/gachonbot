package com.bot.gachon.controller;

import com.bot.gachon.service.GachonService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GachonController {


    private final GachonService gachonService;


    public GachonController( GachonService gachonService){
        this.gachonService = gachonService;
    }



    @GetMapping("/weather")
    public String getWeatherInfo(Model model) {
        model.addAttribute("gachon", gachonService.findAllDesc());
        return "";
    }

    @GetMapping("/haksik")
    public String getHaksikInfo(Model model) {
        model.addAttribute("gachon", gachonService.findHaksik());
        return "";
    }



}