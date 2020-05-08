package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;
import org.apache.tomcat.util.digester.ArrayStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@Getter

public class HaksikResponse {

    private String version;
    private HashMap<String,Object> template = new HashMap<>();

    @Builder
    public HaksikResponse(String menu){
     this.version ="2.0";

     HashMap<String,String> simpleImage = new HashMap<>();
     simpleImage.put("imageUrl","https://s3.ap-northeast-2.amazonaws.com/gachonbot/art.png");
     simpleImage.put("altText","image");
     ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();

     HashMap<String,Object> output = new HashMap<>();
     output.put("simpleImage",simpleImage);
     HashMap<String,Object> output2 = new HashMap<>();
     HashMap<String,Object> simpleText = new HashMap<>();
     simpleText.put("text",menu);
     outputs.add(output);
     outputs.add(output2);
     ArrayList<HashMap<String,String>> quickReplies = new ArrayList<>();
     HashMap<String,String> output3 = new HashMap<>();
     output3.put("action","block");
     output3.put("label","🏠");
     output3.put("messageText","오늘의 식단(학식)보기");
     output3.put("blockId","5c612b6905aaa7668df7bfc1");
     HashMap<String,String> output4 = new HashMap<>();
     output4.put("action","block");
     output4.put("label","🏠");
     output4.put("messageText","🏠");
     output4.put("blockId","5c6aceb7384c5541a0ee5bcc");
     quickReplies.add(output3);
     quickReplies.add(output4);
     this.template.put("outputs",outputs);
     this.template.put("quickReplies",quickReplies);




    }
}
