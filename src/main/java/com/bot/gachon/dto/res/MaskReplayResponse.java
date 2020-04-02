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
    public MaskReplayResponse(){
//        this.version = "2.0";
//
//        HashMap<String,Object> carusel = new HashMap<>();
//        carusel.put("type","basicCard");
//        MaskReplyResponse_sub item1 = MaskReplyResponse_sub.builder().label("마스크재고조회").blockId("마스크재고조회").build();
//        MaskReplyResponse_sub item2 = MaskReplyResponse_sub.builder().label("어제마스크입고시간조회").blockId("어제마스크입고시간").build();
//        ArrayList<MaskReplyResponse_sub> items = new ArrayList<>();
//        items.add(item1);
//        items.add(item2);
//        carusel.put("items",items);
//        HashMap<String,HashMap<String,Object>> outputs_sub = new HashMap<>();
//        outputs_sub.put("carousel",carusel);
//        ArrayList<HashMap<String,HashMap<String,Object>>> outputs = new ArrayList<>();
//        outputs.add(outputs_sub);
//        this.template.put("outputs",outputs);


        this.version = "2.0";
        ArrayList<HashMap<String,Object>> itemsParent = new ArrayList<>();
        HashMap<String,Object> itemsChilde =new HashMap<>();
        itemsChilde.put("title","보물상자");
        itemsChilde.put("description","보물상자 안에는 뭐가 있을까");
        HashMap<String,String> thumbnail = new HashMap<>();
        thumbnail.put("imageUrl","http://k.kakaocdn.net/dn/83BvP/bl20duRC1Q1/lj3JUcmrzC53YIjNDkqbWK/i_6piz1p.jpg");
        itemsChilde.put("thumbnail",thumbnail);
        itemsParent.add(itemsChilde);
        HashMap<String,Object> carousel = new HashMap<>();
        carousel.put("type","basicCard");
        carousel.put("items",itemsParent);
        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        HashMap<String,Object> output = new HashMap<>();
        output.put("carousel",carousel);
        outputs.add(output);
        this.template.put("outputs", outputs);



    }

}
