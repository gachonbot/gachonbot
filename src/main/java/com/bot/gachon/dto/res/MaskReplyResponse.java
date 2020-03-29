package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class MaskReplyResponse {

    private String version;
    private HashMap<String, List<HashMap<String, HashMap<String, String>>>> template = new HashMap<>();

    public MaskReplyResponse(){}

    @Builder
    public MaskReplyResponse(String content){
        this.version = "2.0";
        HashMap<String, String> text = new HashMap<>();
        text.put("text", content);
        HashMap<String, HashMap<String, String>> basicCard = new HashMap<>();
        basicCard.put("basicCard", text);
        ArrayList<HashMap<String, HashMap<String, String>>> outputs = new ArrayList<>();
        outputs.add(basicCard);
        this.template.put("outputs", outputs);
    }
}
