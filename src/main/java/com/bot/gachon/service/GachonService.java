package com.bot.gachon.service;

import com.bot.gachon.dto.response.HaksikDto;
import com.bot.gachon.dto.response.HaksikSubDto;
import com.bot.gachon.dto.response.WeatherDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
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

@RequiredArgsConstructor
@Service
public class GachonService {

    private Logger logger = LoggerFactory.getLogger(GachonService.class);

    public List<WeatherDto> findAllDesc() {
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

    public String findHaksik(){
        try {
            Document doc = Jsoup.connect("http://m.gachon.ac.kr/menu/menu.jsp?gubun=C").get();
            Element e = doc.getElementById("toggle-view");

            JSONObject haksikObject = new JSONObject(); // 학식전체
            JSONArray allMenuObject = new JSONArray(); // 월화수목금메뉴전체
            JSONObject menuObject = null; // 메뉴


            for(Element child : e.children()){
                menuObject = new JSONObject(); // 메뉴
                menuObject.put("day",child.getElementsByTag("img").attr("alt"));
                menuObject.put("menu",child.text());//요일
                allMenuObject.put(menuObject);
                haksikObject.put("allMenu", allMenuObject);
            }

            ObjectMapper mapper = new ObjectMapper();
            HaksikDto haksikDto = mapper.readValue(haksikObject.toString(),HaksikDto.class);
            for(HaksikSubDto sub : haksikDto.getAllMenu()){
                logger.info("# day : " + sub.getDay());
                logger.info("# menu : " + sub.getMenu());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

