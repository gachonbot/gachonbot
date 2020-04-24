package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
@Getter
public class GuideResponse {
    private String version;
    private HashMap<String,Object> template = new HashMap<>();


    @Builder
    public GuideResponse(String menu,Object items){

        this.version = "2.0";
        HashMap<String,String> header = new HashMap<>();
        header.put("title",menu);
        header.put("imageUrl","https://s3.ap-northeast-2.amazonaws.com/gachonbot/notice.png");
        HashMap<String,Object> listCard = new HashMap<>();
        listCard.put("header",header);
        listCard.put("items",items);
        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        HashMap<String,Object> output = new HashMap<>();
        output.put("listCard",listCard);
        outputs.add(output);
        this.template.put("outputs",outputs);






    }
}
