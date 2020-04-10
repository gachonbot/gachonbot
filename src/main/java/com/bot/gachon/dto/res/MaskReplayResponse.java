package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class MaskReplayResponse {
    private String version;
    private HashMap<String,Object> template = new HashMap<>();


    @Builder
    public MaskReplayResponse(Object items){

        this.version = "2.0";

        HashMap<String,Object> carousel = new HashMap<>();
        carousel.put("type","basicCard");
        carousel.put("items",items);
        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        HashMap<String,Object> output = new HashMap<>();
        output.put("carousel",carousel);
        outputs.add(output);
        this.template.put("outputs", outputs);



    }
}
