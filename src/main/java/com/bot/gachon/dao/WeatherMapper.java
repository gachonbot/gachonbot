package com.bot.gachon.dao;

import com.bot.gachon.dto.res.WeatherDto;
import com.bot.gachon.util.constant.Url;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
@Component
@Slf4j
public class WeatherMapper {

    private final RestTemplate restTemplate;


    public WeatherMapper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherDto getWeatherInfo2() {

        URI url = URI.create(Url.WEATHER_URL);
        WeatherDto responseEntity = null;
        responseEntity = restTemplate.getForObject(url, WeatherDto.class);

        return responseEntity;
    }
}

