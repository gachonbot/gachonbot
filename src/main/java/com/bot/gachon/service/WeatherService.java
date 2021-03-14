package com.bot.gachon.service;

import com.bot.gachon.dao.WeatherMapper;
import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.WeatherDto;
import com.bot.gachon.dto.res.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    private final WeatherMapper weatherMapper;

    public WeatherService(RestTemplate restTemplate ,WeatherMapper weatherMapper) {
        this.restTemplate = restTemplate;
        this.weatherMapper =weatherMapper;
    }

    public WeatherResponse getWeatherInfo2(BotRequest botRequest) {
        WeatherDto mapper = weatherMapper.getWeatherInfo2();

        return WeatherResponse.builder().status(mapper.getWeather().get(0).getDescription())
                .detail("현재기온: " + mapper.getMain().getTemp() + "ºC\n"
                        + "오늘의 최고기온: " + mapper.getMain().getTemp_max() + "ºC\n"
                        + "오늘의 최저기온: " + mapper.getMain().getTemp_min() + "ºC\n"
                        + "습도: " + mapper.getMain().getHumidity()+"%").build();


    }


}
