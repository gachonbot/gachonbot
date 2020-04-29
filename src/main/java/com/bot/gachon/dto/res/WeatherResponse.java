package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
@Getter
public class WeatherResponse {
    private String version;
    private HashMap<String,Object> template = new HashMap<>();


    @Builder
    public WeatherResponse(String data,String status,String detail){
        this.version = "2.0";

        HashMap<String,String> simpleText = new HashMap<>();
        simpleText.put("text",data);

        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        HashMap<String,Object> output = new HashMap<>();
        output.put("simpleText",simpleText);

        HashMap<String,Object> basicCard = new HashMap<>();
        basicCard.put("title",status);
        basicCard.put("description",detail);
        HashMap<String,String> thumbnail = new HashMap<>();
        thumbnail.put("imageUrl","https://s3.ap-northeast-2.amazonaws.com/gachonbot/sunny.jpg");
        basicCard.put("thumbnail",thumbnail);
        HashMap<String,Object> output2  = new HashMap<>();
        output2.put("basicCard",basicCard);
        outputs.add(output2);
        outputs.add(output);

        this.template.put("outputs",outputs);



    }
}