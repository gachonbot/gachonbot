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




        HashMap<String,String> simpleText = new HashMap<>();
        simpleText.put("text","안녕하세요! 가천봇입니다.");
        HashMap<String,String> thumbnail = new HashMap<>();
        thumbnail.put("imageUrl","https://s3.ap-northeast-2.amazonaws.com/gachonbot/main1.png");

        ArrayList<HashMap<String,String>> buttonsParent = new ArrayList<>();

        HashMap<String,String> buttonsChild1 = new HashMap<>();
        buttonsChild1.put("action","message");
        buttonsChild1.put("label", "학사메뉴");
        buttonsChild1.put("messageText","학사메뉴");


        HashMap<String,String> buttonsChild2 = new HashMap<>();
        buttonsChild2.put("action","message");
        buttonsChild2.put("label", "공지사항");
        buttonsChild2.put("messageText","공지사항");

        HashMap<String,String> buttonsChild3 = new HashMap<>();
        buttonsChild3.put("action","message");
        buttonsChild3.put("label", "다음 무당이?");
        buttonsChild3.put("messageText","다음 무당이?");


        buttonsParent.add(buttonsChild1);
        buttonsParent.add(buttonsChild2);
        buttonsParent.add(buttonsChild3);





        HashMap<String,String> thumbnail2 = new HashMap<>();
        thumbnail2.put("imageUrl","https://s3.ap-northeast-2.amazonaws.com/gachonbot/main2.png");




        ArrayList<HashMap<String,String>> buttonsParent2 = new ArrayList<>();

        HashMap<String,String> buttonsChild4 = new HashMap<>();
        buttonsChild4.put("action","message");
        buttonsChild4.put("label", "실시간 날씨");
        buttonsChild4.put("messageText","실시간 날씨");


        HashMap<String,String> buttonsChild5 = new HashMap<>();
        buttonsChild5.put("action","message");
        buttonsChild5.put("label", "실시간 미세먼지");
        buttonsChild5.put("messageText","실시간 미세먼지");

        HashMap<String,String> buttonsChild6= new HashMap<>();
        buttonsChild6.put("action","message");
        buttonsChild6.put("label", "도서관 자리현황");
        buttonsChild6.put("messageText","도서관 자리현황");

        HashMap<String,String> thumbnail3 = new HashMap<>();
        thumbnail3.put("imageUrl","https://s3.ap-northeast-2.amazonaws.com/gachonbot/main3.png");

        ArrayList<HashMap<String,String>> buttonsParent3 = new ArrayList<>();

        HashMap<String,String> buttonsChild7 = new HashMap<>();
        buttonsChild7.put("action","message");
        buttonsChild7.put("label", "맛집 메뉴");
        buttonsChild7.put("messageText","맛집 메뉴");

        HashMap<String,String> buttonsChild8 = new HashMap<>();
        buttonsChild8.put("action","message");
        buttonsChild8.put("label", "오늘 뭐 먹지?");
        buttonsChild8.put("messageText","오늘 뭐 먹지?");

        HashMap<String,String> buttonsChild9= new HashMap<>();
        buttonsChild9.put("action","message");
        buttonsChild9.put("label", "오늘의 식단(학식)");
        buttonsChild9.put("messageText","오늘의 식단(학식)");


        buttonsParent2.add(buttonsChild4);
        buttonsParent2.add(buttonsChild5);
        buttonsParent2.add(buttonsChild6);

        buttonsParent3.add(buttonsChild7);
        buttonsParent3.add(buttonsChild8);
        buttonsParent3.add(buttonsChild9);




        ArrayList<HashMap<String,Object>> items = new ArrayList<>();

       HashMap<String,Object> item = new HashMap<>();
       item.put("thumbnail",thumbnail);
       item.put("buttons",buttonsParent);
       items.add(item);

        HashMap<String,Object> item2 = new HashMap<>();
        item2.put("thumbnail",thumbnail2);
        item2.put("buttons",buttonsParent2);
        items.add(item2);
        HashMap<String,Object> item3 = new HashMap<>();
        item3.put("thumbnail",thumbnail3);
        item3.put("buttons",buttonsParent3);
        items.add(item3);

        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();

        HashMap<String,Object> output = new HashMap<>();
        output.put("simpleText",simpleText);


        HashMap<String,Object>output2 = new HashMap<>();

        HashMap<String,Object> carousel = new HashMap<>();

        carousel.put("type","basicCard");
        carousel.put("items",items);
        output2.put("carousel",carousel);
        outputs.add(output);
        outputs.add(output2);



        this.template.put("outputs",outputs);




    }
}
