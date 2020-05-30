package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
@Getter
public class ScheudleMonthMenu_D {


    private String version;
    private HashMap<String, Object> template = new HashMap<>();

    @Builder
    public ScheudleMonthMenu_D() {
        this.version = "2.0";
        HashMap<String, String> simpleText = new HashMap<>();
        simpleText.put("text", "달을 선택해주세요!");
        HashMap<String, Object> output = new HashMap<>();
        output.put("simpleText", simpleText);
        ArrayList<HashMap<String, Object>> outputs = new ArrayList<>();
        outputs.add(output);
        ArrayList<HashMap<String, String>> quickReplies = new ArrayList<>();
        HashMap<String,String> output9 = new HashMap<>();
        output9.put("action","block");
        output9.put("label","9월");
        output9.put("messageText","9월");
        output9.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String, String> output10 = new HashMap<>();
        output10.put("action", "block");
        output10.put("label", "10월");
        output10.put("messageText", "10월");
        output10.put("blockId", "5ece22cbe0d4e2000176602a");
        HashMap<String, String> output11 = new HashMap<>();
        output11.put("action", "block");
        output11.put("label", "11월");
        output11.put("messageText", "11월");
        output11.put("blockId", "5ece22cbe0d4e2000176602a");
        HashMap<String, String> output12 = new HashMap<>();
        output12.put("action", "block");
        output12.put("label", "11월");
        output12.put("messageText", "11월");
        output12.put("blockId", "5ece22cbe0d4e2000176602a");
        HashMap<String, String> output_b = new HashMap<>();
        output_b.put("action", "block");
        output_b.put("label", "이전");
        output_b.put("messageText", "이전");
        output_b.put("blockId", "5ed24fb51276c300013197d9");
        quickReplies.add(output9);
        quickReplies.add(output10);
        quickReplies.add(output11);
        quickReplies.add(output12);
        quickReplies.add(output_b);

        this.template.put("quickReplies",quickReplies);
        this.template.put("outputs",outputs);

    }
}
