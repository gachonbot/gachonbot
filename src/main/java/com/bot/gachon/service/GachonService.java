package com.bot.gachon.service;

import com.bot.gachon.dto.response.HaksikDto;
import com.bot.gachon.dto.response.HaksikSubDto;
import com.bot.gachon.dto.response.MaskDto;
import com.bot.gachon.dto.response.WeatherDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RequiredArgsConstructor
@Service
public class GachonService {


    public WeatherDto findWeatherInfo() throws Exception {


        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(Url.WEATHER_URL);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.getForEntity(url, String.class);

        String jsonInfo = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        WeatherDto weatherDto = mapper.readValue(jsonInfo, WeatherDto.class);
        return weatherDto;
    }


    public HaksikDto findHaksikInfo(String building) throws IOException {

        HaksikUrl haksikUrl = HaksikUrl.valueOf(building);
        Document doc = Jsoup.connect(haksikUrl.link).get();
        Element e = doc.getElementById("toggle-view");
        HaksikDto haksikDto = new HaksikDto();
        List<HaksikSubDto> haksikSubDtoList = new ArrayList<>();

        String today = new SimpleDateFormat("E요일").format(new Date());

        for (Element child : e.children()) {
            if (today.equals(child.getElementsByTag("img").attr("alt"))) {
                HaksikSubDto haksikSubDto = new HaksikSubDto();
                haksikSubDto.setDay(child.getElementsByTag("img").attr("alt"));
                haksikSubDto.setMenu(child.text());
                haksikSubDtoList.add(haksikSubDto);
                break;
            }
        }

        haksikDto.setAllMenu(haksikSubDtoList);
        return haksikDto;
    }

    public MaskDto findMaskInfo() throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(Url.MASK_URL);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String jsonInfo2 = responseEntity.getBody();

        System.out.println("# 결과 : " + jsonInfo2);
        ObjectMapper mapper = new ObjectMapper();
        MaskDto maskDto = mapper.readValue(jsonInfo2, MaskDto.class);

        return maskDto;
    }
}


