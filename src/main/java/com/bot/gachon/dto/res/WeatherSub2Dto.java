package com.bot.gachon.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class WeatherSub2Dto {
    private Long temp;
    private Long temp_min;
    private Long temp_max;
    private Long humidity;

    public WeatherSub2Dto(){}
    @Builder
    public WeatherSub2Dto(Long temp, Long temp_max, Long temp_min,Long humidity){
        this.temp = temp;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.humidity = humidity;
    }


}
