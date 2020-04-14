package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
@Getter
public class MainMenuDto {

    private String version;
    private HashMap<String,Object> template = new HashMap<>();

    @Builder
    public MainMenuDto(){
        this.version = "2.0";

        HashMap<String,String> thumbnail = new HashMap<>();
        thumbnail.put("imageUrl","https://hswsns.s3.ap-northeast-2.amazonaws.com/img/portfolio/mask4.jpg");
        HashMap<String,Object> basicCard = new HashMap<>();
        basicCard.put("thumbnail",thumbnail);
        HashMap<String,String> simpleText = new HashMap<>();
        simpleText.put("text","안녕하세요! 가천봇입니다.");
        ArrayList<HashMap<String,String>> buttonsParent = new ArrayList<>();
        HashMap<String,String> buttonsChild1 = new HashMap<>();
        buttonsChild1.put("action","message");
        buttonsChild1.put("label", "공지사항");
        buttonsChild1.put("messageText","공지사항");
        buttonsParent.add(buttonsChild1);

        HashMap<String,String> buttonsChild2 = new HashMap<>();
        buttonsChild2.put("action","message");
        buttonsChild2.put("label", "무당이");
        buttonsChild2.put("messageText","무당이");

        buttonsParent.add(buttonsChild2);
        basicCard.put("buttons",buttonsParent);

        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        HashMap<String,Object> output = new HashMap<>();
        output.put("simpleText",simpleText);
        HashMap<String,Object> output2 = new HashMap<>();
        output2.put("basicCard",basicCard);
        outputs.add(output);
        outputs.add(output2);
        this.template.put("outputs",outputs);




    }
}
