package com.bot.gachon.dto.res;


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

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<WeatherSub1Dto> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherSub1Dto> weather) {
        this.weather = weather;
    }

    public WeatherSub2Dto getMain() {
        return main;
    }

    public void setMain(WeatherSub2Dto main) {
        this.main = main;
    }
}




