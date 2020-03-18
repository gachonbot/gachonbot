package com.bot.gachon.service;

import com.bot.gachon.dto.response.DustDto;
import com.bot.gachon.dto.response.HaksikDto;
import com.bot.gachon.dto.response.WeatherDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class GachonService {

    private final RestTemplate restTemplate;

    public GachonService(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    public WeatherDto findWeatherInfo() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(Url.DUST_URL);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.getForEntity(url, String.class);

        String jsonInfo = responseEntity.getBody();
        Map<String, Object> result = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        WeatherDto weatherDto = mapper.readValue(jsonInfo, WeatherDto.class);
        return weatherDto;
    }

    public HaksikDto findHaksikInfo() throws IOException {
        Document doc = Jsoup.connect(Url.HAKSIK_URL).get();

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

    public DustDto findDust() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(Url.DUST_URL);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.getForEntity(url, String.class);

        String jsonInfo = responseEntity.getBody();
        Map<String, Object> result = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        DustDto dustDto = mapper.readValue(jsonInfo, DustDto.class);
        return dustDto;

         //DustModel response = restTemplate.getForObject(Url.WEATHER_URL, DustModel.class);
         //return response;
    }
}


