package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class MaskReplayResponse {
    private String version;

    private HashMap<String,List<HashMap<String,HashMap<String,Object>>>> template = new HashMap<>();

    public MaskReplayResponse(){}

    @Builder
    public MaskReplayResponse(String content){
        this.version = "2.0";
        HashMap<String,Object> type = new HashMap<>();
        type.put("type",content);

        MaskReplyResponse_sub item1 = MaskReplyResponse_sub.builder().label("마스크재고조회").blockId("마스크재고조회").build();
        MaskReplyResponse_sub item2 = MaskReplyResponse_sub.builder().label("어제마스크입고시간조회").blockId("어제마스크입고시간").build();
        ArrayList<MaskReplyResponse_sub> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        type.put("item",items);
        HashMap<String,HashMap<String,Object>> carousel = new HashMap<>();
        carousel.put("carousel",type);
//        carousel.put("item",item);
        ArrayList<HashMap<String,HashMap<String,Object>>> outputs = new ArrayList<>();
        outputs.add(carousel);
        this.template.put("template",outputs);




    }

}
