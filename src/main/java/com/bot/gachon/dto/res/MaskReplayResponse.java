package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class MaskReplayResponse {
    private String version;
    private HashMap<String,Object> template = new HashMap<>();


    @Builder
    public MaskReplayResponse(Object items){

        this.version = "2.0";
//        ArrayList<HashMap<String,Object>> itemsParent = new ArrayList<>();
//        HashMap<String,Object> itemsChilde =new HashMap<>();
//        itemsChilde.put("title","보물상자");
//        itemsChilde.put("description","보물상자 안에는 뭐가 있을까");
//        HashMap<String,String> thumbnail = new HashMap<>();
//        thumbnail.put("imageUrl","http://k.kakaocdn.net/dn/83BvP/bl20duRC1Q1/lj3JUcmrzC53YIjNDkqbWK/i_6piz1p.jpg");
//        itemsChilde.put("thumbnail",thumbnail);
//
//
//        HashMap<String,Object> itemsChilde2 = new HashMap<>();
//        itemsChilde2.put("title","마스크");
//        itemsChilde2.put("description","마스크조회하세요");
//        HashMap<String,String> thumbnail2 = new HashMap<>();
//        thumbnail2.put("imageUrl","http://k.kakaocdn.net/dn/83BvP/bl20duRC1Q1/lj3JUcmrzC53YIjNDkqbWK/i_6piz1p.jpg");
//        itemsChilde2.put("thumbnail",thumbnail2);

//        itemsParent.add(itemsChilde);
//        itemsParent.add(itemsChilde2);

        HashMap<String,Object> carousel = new HashMap<>();
        carousel.put("type","basicCard");
//        carousel.put("items",itemsParent);
        carousel.put("items",items);
        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        HashMap<String,Object> output = new HashMap<>();
        output.put("carousel",carousel);
        outputs.add(output);
        this.template.put("outputs", outputs);



    }

}
