package com.bot.gachon.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class WeatherSub2Dto {
    private Double temp;
    private Double temp_min;
    private Double temp_max;
    private Long humidity;

    public WeatherSub2Dto(){}
    @Builder
    public WeatherSub2Dto(Double temp, Double temp_max, Double temp_min,Long humidity){
        this.temp = temp;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.humidity = humidity;
    }


}
