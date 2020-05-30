package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
@Getter
public class ScheudleMonthMenu {

    private String version;
    private HashMap<String,Object> template = new HashMap<>();

    @Builder
    public ScheudleMonthMenu() {
        this.version = "2.0";
        HashMap<String,String> simpleText = new HashMap<>();
        simpleText.put("tesx","달을 선택해주세요!");
        HashMap<String,Object> output = new HashMap<>();
        output.put("simpleText",simpleText);
        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        outputs.add(output);
        ArrayList<HashMap<String,String>> quickReplies = new ArrayList<>();
        HashMap<String,String> output1 = new HashMap<>();
        output1.put("action","block");
        output1.put("label","1월");
        output1.put("messageText","1월");
        output1.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output2 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","2월");
        output2.put("messageText","2월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output3 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","3월");
        output2.put("messageText","3월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output4 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","4월");
        output2.put("messageText","4월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output5 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","5월");
        output2.put("messageText","5월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output6 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","6월");
        output2.put("messageText","6월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output7 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","7월");
        output2.put("messageText","7월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output8 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","8월");
        output2.put("messageText","8월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output9 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","9월");
        output2.put("messageText","9월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output10 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","10월");
        output2.put("messageText","10월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output11 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","11월");
        output2.put("messageText","11월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        HashMap<String,String> output12 = new HashMap<>();
        output2.put("action","block");
        output2.put("label","12월");
        output2.put("messageText","12월");
        output2.put("blockId","5ece22cbe0d4e2000176602a");
        quickReplies.add(output1);
        quickReplies.add(output2);
        quickReplies.add(output3);
        quickReplies.add(output4);
        quickReplies.add(output5);
        quickReplies.add(output6);
        quickReplies.add(output7);
        quickReplies.add(output8);
        quickReplies.add(output9);
        quickReplies.add(output10);
        quickReplies.add(output11);
        quickReplies.add(output12);
        this.template.put("quickReplies",quickReplies);
        this.template.put("outputs",outputs);

    }

}
