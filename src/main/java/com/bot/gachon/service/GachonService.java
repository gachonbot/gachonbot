package com.bot.gachon.service;

import com.bot.gachon.domain.Gachon;
import com.bot.gachon.dto.response.GachonResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class GachonService {

    private Logger logger = LoggerFactory.getLogger(GachonService.class);

    public List<GachonResponseDto> findAllDesc() {
        String restUrl = "http://api.openweathermap.org/data/2.5/weather?id=1897000&APPID=08275712021d8895752cf798fb603cde&lang=kr&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(restUrl);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.getForEntity(url, String.class);

      String jsonInfo = responseEntity.getBody();
        logger.info("## openweathermap json result## : \n\n" + jsonInfo);
        try {
            Map<String, Object> result = new HashMap<>();

            ObjectMapper mapper = new ObjectMapper();
            GachonResponseDto gachonResponseDto = mapper.readValue(jsonInfo, GachonResponseDto.class);
            logger.info(gachonResponseDto.getName());
            logger.info(gachonResponseDto.getWeather().get(0).getDescription());
            logger.info(String.valueOf(gachonResponseDto.getMain().getTemp()));
            logger.info(String.valueOf(gachonResponseDto.getMain().getHumidity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

