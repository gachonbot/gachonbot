package com.bot.gachon.service;

import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.WeatherDto;
import com.bot.gachon.dto.res.WeatherResponse;
import com.bot.gachon.url.Url;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
@Slf4j
@Service
public class Weather {
    private final RestTemplate restTemplate;

    public Weather(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeatherInfo2(BotRequest botRequest) {

        URI url = URI.create(Url.WEATHER_URL);
        WeatherDto responseEntity = null;
        responseEntity = restTemplate.getForObject(url, WeatherDto.class);


        return WeatherResponse.builder().status(responseEntity.getWeather().get(0).getDescription())
                .detail("현재기온: " + responseEntity.getMain().getTemp() + "ºC\n"
                        + "오늘의 최고기온: " + responseEntity.getMain().getTemp_max() + "ºC\n"
                        + "오늘의 최저기온: " + responseEntity.getMain().getTemp_min() + "ºC\n"
                        + "습도: " + responseEntity.getMain().getHumidity()+"%").build();


    }


}
