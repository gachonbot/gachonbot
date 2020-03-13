package com.bot.gachon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@RestController
public class GachonController {

    @GetMapping("/dust")
    public Object callAPI() throws JsonProcessingException, UnsupportedEncodingException {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);

            String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst";
            String serviceKey = "P4zgBHeiRu9%2B499IV12fo7S463t6lMsVwSGZqk1mVWYVzNnpcGrp8MMCwZg2R6cmBiT4eSqmdj%2BrnAl59wkdJg%3D%3D";
            String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");

            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("serviceKey", decodeServiceKey)
                .queryParam("sidoName", "서울")
                .queryParam("searchCondition", "DAILY")
                .queryParam("_returnType", "json")
                .build(false);

            Object response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(header), String.class);
        return response;
    }
}

