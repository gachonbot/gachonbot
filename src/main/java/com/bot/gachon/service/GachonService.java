package com.bot.gachon.service;

import com.bot.gachon.dto.Dust;
import com.bot.gachon.dto.response.DustModel;
import com.bot.gachon.dto.response.DustRepository;
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

    private final DustRepository dustRepository;

    private final RestTemplate restTemplate;

    public GachonService(DustRepository dustRepository, RestTemplate restTemplate) {
        this.dustRepository = dustRepository;
        this.restTemplate = restTemplate;}


    public DustModel findDust() throws IOException {
        DustModel response = restTemplate.getForObject(Url.DUST_URL, DustModel.class);
        return response;
    }

    public void saveDust(Dust dust){
        dustRepository.save(dust);
    }
}


