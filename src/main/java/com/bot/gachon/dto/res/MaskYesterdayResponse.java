package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class MaskYesterdayResponse {

    private String version;
    private HashMap<String, List<HashMap<String, HashMap<String, String>>>> template = new HashMap<>();

    public MaskYesterdayResponse(){}

    @Builder
    public MaskYesterdayResponse(String content){
        this.version = "2.0";
        HashMap<String, String> text = new HashMap<>();
        text.put("text", content);
        HashMap<String, HashMap<String, String>> simpleText = new HashMap<>();
        simpleText.put("simpleText", text);
        ArrayList<HashMap<String, HashMap<String, String>>> outputs = new ArrayList<>();
        outputs.add(simpleText);
        this.template.put("outputs", outputs);
    }
}
