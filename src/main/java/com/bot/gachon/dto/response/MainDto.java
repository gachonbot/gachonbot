package com.bot.gachon.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MainDto {
    private Double temp;
    private Long humidity;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }
}
