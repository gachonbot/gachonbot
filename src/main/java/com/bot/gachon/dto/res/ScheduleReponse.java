package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
@Getter
public class ScheduleReponse {
    private String version;
    private HashMap<String,Object> template = new HashMap<>();
    @Builder
    public ScheduleReponse(String content,String image){
        this.version = "2.0";


        HashMap<String,String> simpleText = new HashMap<>();
        simpleText.put("text", content);
        HashMap<String,Object> simpleImage = new HashMap<>();
        simpleImage.put("imageUrl",image);
        HashMap<String,Object> output2 = new HashMap<>();
        output2.put("simpleImage",simpleImage);

        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        outputs.add(simpleImage);
        HashMap<String,Object> output = new HashMap<>();
        output.put("simpleText",simpleText);
        outputs.add(output);
        this.template.put("outputs",outputs);


    }
}
