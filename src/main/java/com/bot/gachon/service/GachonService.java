package com.bot.gachon.service;

import com.bot.gachon.dto.response.HaksikDto;
import com.bot.gachon.dto.response.HaksikSubDto;
import com.bot.gachon.dto.response.WeatherDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bot.gachon.service.Url.restUrl;
import static com.bot.gachon.service.Url.restUrl2;

@RequiredArgsConstructor
@Service
public class GachonService {

    private Logger logger = LoggerFactory.getLogger(GachonService.class);

    public List<WeatherDto> findAllDesc() {


        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(restUrl);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.getForEntity(url, String.class);

        String jsonInfo = responseEntity.getBody();
        logger.info("## openweathermap json result## : \n\n" + jsonInfo);
        try {
            Map<String, Object> result = new HashMap<>();

            ObjectMapper mapper = new ObjectMapper();
            WeatherDto weatherDto = mapper.readValue(jsonInfo, WeatherDto.class);
            logger.info(weatherDto.getName());
            logger.info(weatherDto.getWeather().get(0).getDescription());
            logger.info(String.valueOf(weatherDto.getMain().getTemp()));
            logger.info(String.valueOf(weatherDto.getMain().getHumidity()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public HaksikDto findHaksik() throws IOException {
        Document doc = Jsoup.connect(restUrl2).get();

        Element e = doc.getElementById("toggle-view");

        JSONObject haksikObject = new JSONObject();
        JSONArray allMenuObject = new JSONArray();
        JSONObject menuObject = null;

        for (Element child : e.children()) {
            menuObject = new JSONObject();
            menuObject.put("day", child.getElementsByTag("img").attr("alt"));
            menuObject.put("menu", child.text());
            allMenuObject.add(menuObject);
            haksikObject.put("allMenu", allMenuObject);
        }
        ObjectMapper mapper = new ObjectMapper();
        HaksikDto haksikDto = mapper.readValue(haksikObject.toString(), HaksikDto.class);


        return haksikDto;

    }
}


