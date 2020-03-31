package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Builder
@Getter
public class MaskReplayResponse {
    private String version;

    private HashMap<String,List<HashMap<String,HashMap<String,Object>>>> template = new HashMap<>();
    private List<MaskReplyResponse_sub> item;

    public MaskReplayResponse(){}

    @Builder
    public MaskReplayResponse(String content){
        this.version = "2.0";
        HashMap<String,Object> type = new HashMap<>();
        type.put("type",content);
        type.put("item",item);
        HashMap<String,HashMap<String,Object>> carousel = new HashMap<>();
        carousel.put("carousel",type);
//        carousel.put("item",item);
        ArrayList<HashMap<String,HashMap<String,Object>>> outputs = new ArrayList<>();
        outputs.add(carousel);
        this.template.put("template",outputs);




    }

}
