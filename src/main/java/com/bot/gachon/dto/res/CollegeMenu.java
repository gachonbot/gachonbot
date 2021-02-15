package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
@Getter
public class CollegeMenu {


    private String version;
    private HashMap<String, Object> template = new HashMap<>();

    @Builder
    public CollegeMenu() {
        this.version = "2.0";
        HashMap<String,String> simpleText = new HashMap<>();
        simpleText.put("text","단과대학을 선택해주세요!");
        HashMap<String,Object> output = new HashMap<>();
        output.put("simpleText",simpleText);
        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        outputs.add(output);
        ArrayList<HashMap<String,String>> quickReplies = new ArrayList<>();
        HashMap<String,String> output1 = new HashMap<>();
        output1.put("action","block");
        output1.put("label","1월");
    }
}

