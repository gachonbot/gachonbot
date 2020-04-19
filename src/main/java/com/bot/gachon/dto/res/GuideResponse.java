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
    public GuideResponse(Object items){

        this.version = "2.0";
        HashMap<String,String> header = new HashMap<>();
        header.put("title","공지사항");
        header.put("imageUrl","http://k.kakaocdn.net/dn/xsBdT/btqqIzbK4Hc/F39JI8XNVDMP9jPvoVdxl1/2x1.jpg");
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
