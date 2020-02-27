package com.bot.gachon.controller;

import com.bot.gachon.dto.response.GachonResponseDto;
import com.bot.gachon.service.GachonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GachonController {


    private final GachonService gachonService;


    public GachonController( GachonService gachonService){
        this.gachonService = gachonService;
    }



    @GetMapping("/weather")
    public String index(Model model) {
        model.addAttribute("gachon", gachonService.findAllDesc());
        return "";
    }



}