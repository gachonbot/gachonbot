package com.bot.gachon.service;

import com.bot.gachon.domain.Gachon;
import com.bot.gachon.dto.response.GachonResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GachonService {


    public List<GachonResponseDto> findAll() {
        return this.findAllDesc();
    }

    private List<GachonResponseDto> findAllDesc(){

        String restUrl = "http://api.openweathermap.org/data/2.5/weather?id=1897000&APPID=08275712021d8895752cf798fb603cde&lang=kr&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(restUrl);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.getForEntity(url, String.class);

        String jsonInfo = responseEntity.getBody();
        System.out.println("## openweathermap json result## : \n\n" + jsonInfo);

        String location = "";
        String des = "";
        String des2 = "";
        Double tmp = 0.0;
        Long hum = null;

        try {

            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);

            JSONArray weatherJsonInfo = (JSONArray) jsonObject.get("weather");
            JSONObject weatherObject = (JSONObject) weatherJsonInfo.get(0);
            JSONObject mainJsonInfo = (JSONObject) jsonObject.get("main");
            String name = (String) jsonObject.get("name");

            System.out.println("INFO");
            System.out.println("weatherObject: main==>"+weatherObject.get("main"));
            System.out.println("weatherObject: main==>"+weatherObject.get("description"));
            System.out.println("weatherObject: main==>"+mainJsonInfo.get("temp"));
            System.out.println("weatherObject: main==>"+mainJsonInfo.get("humidity"));
            System.out.println("weatherObject: name==>"+name);

            location = name;
            des = (String) weatherObject.get("main");
            des2 = (String) weatherObject.get("description");
            tmp =(Double)  mainJsonInfo.get("temp");
            hum = (Long) mainJsonInfo.get("humidity");

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Gachon aaa = new Gachon();
        aaa.setLocation(location);
        aaa.setTmp(tmp);
        aaa.setHum(hum);
        aaa.setDes(des);
        aaa.setDes2(des2);

        GachonResponseDto ddd = new GachonResponseDto(aaa);
        ArrayList<GachonResponseDto> list = new ArrayList<>();
        list.add(ddd);

        return list;
    }
}