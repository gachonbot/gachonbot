package com.bot.gachon.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)

public class GachonResponseDto {
    private String name; //지역
    private ArrayList<WeatherDto> weather;
    private MainDto main;

    public String getName() {
        return name;
    }

    public ArrayList<WeatherDto> getWeather() {
        return weather;
    }

    public MainDto getMain() {
        return main;
    }
}




