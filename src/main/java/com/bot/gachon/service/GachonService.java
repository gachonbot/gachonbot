package com.bot.gachon.service;

import com.bot.gachon.domain.Gachon;
import com.bot.gachon.domain.GachonRepository;
import com.bot.gachon.dto.response.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private final GachonRepository gachonRepository;

    @Transactional
    public MaskDto findMaskInfo(){

        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(Url.MASK_URL);
        MaskDto response = restTemplate.getForObject(url, MaskDto.class);

        ArrayList<Gachon> list = response.toEntitiy();
        for( Gachon gachon : list ){
            gachonRepository.save(gachon);
        }

//        for( MaskSubDto sub : response.getStores() ){
//            gachonRepository.save(response.toEntitiy(sub));
//        }
        return response;

    }
}



