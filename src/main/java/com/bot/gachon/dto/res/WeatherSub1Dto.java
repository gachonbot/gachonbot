package com.bot.gachon.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)

@Getter
public class WeatherSub1Dto {
    private String description;

    public WeatherSub1Dto(){}
    @Builder
    public WeatherSub1Dto(String description){
        this.description = description;
    }
}