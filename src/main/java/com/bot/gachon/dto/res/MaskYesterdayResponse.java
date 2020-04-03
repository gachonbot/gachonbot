package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class MaskYesterdayResponse {

    private String version;
    private HashMap<String,Object> template = new HashMap<>();

    @Builder
    public MaskYesterdayResponse(){
        this.version = "2.0";
        HashMap<String, String> simpleText = new HashMap<>();
        simpleText.put("text","간단한 텍스트요소입니다.");
        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        HashMap<String,Object> output = new HashMap<>();
        output.put("simpleText",simpleText);
        outputs.add(output);
        this.template.put("outputs", output);
    }
}
