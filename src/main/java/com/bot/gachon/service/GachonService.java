package com.bot.gachon.service;

import com.bot.gachon.domain.Gachon;
import com.bot.gachon.domain.GachonRepository;
import com.bot.gachon.dto.response.HaksikDto;
import com.bot.gachon.dto.response.HaksikSubDto;
import com.bot.gachon.dto.response.MaskDto;
import com.bot.gachon.dto.response.WeatherDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@Service
@EnableScheduling
public class GachonService {


    private final GachonRepository gachonRepository;

    public GachonService(GachonRepository gachonRepository){

        this.gachonRepository = gachonRepository;

    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



    public WeatherDto findWeatherInfo() throws Exception {


        URI url = URI.create(Url.WEATHER_URL);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate().getForEntity(url, String.class);

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

    @Scheduled(fixedDelay = 300000)
    public MaskDto updateMaskInfo(){

        URI url = URI.create(Url.MASK_URL);
        MaskDto response = restTemplate().getForObject(url, MaskDto.class);

        ArrayList<Gachon> list = response.toEntitiy();
        for( Gachon gachon : list ){
            gachonRepository.save(gachon);
        }

        return response;
    }

    @Transactional(readOnly = true)
    public List<Gachon> findMaskInfo() {
        return gachonRepository.findAll();
    }
}



