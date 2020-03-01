package com.bot.gachon.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)

public class WeatherDto {
    private String name; //지역
    private ArrayList<WeatherSub1Dto> weather;
    private WeatherSub2Dto main;

    public String getName() {
        return name;
    }

    public ArrayList<WeatherSub1Dto> getWeather() {
        return weather;
    }

    public WeatherSub2Dto getMain() {
        return main;
    }
}




;