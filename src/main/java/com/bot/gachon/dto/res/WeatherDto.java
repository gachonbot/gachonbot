package com.bot.gachon.dto.res;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class WeatherDto {
    private String name; //지역
    private ArrayList<WeatherSub1Dto> weather;
    private WeatherSub2Dto main;

    public WeatherDto(){}

    @Builder
    public WeatherDto(String name, ArrayList<WeatherSub1Dto> weather, WeatherSub2Dto main){
        this.name = name;
        this.weather = weather;
        this.main = main;
    }
}




