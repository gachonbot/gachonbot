package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Getter
public class MaskMenuDto {
    private String version;
    private HashMap<String, Object> template = new HashMap<>();

    @Builder
    public MaskMenuDto(){
        this.version = "2.0";

        HashMap<String, Object> basicCard = new HashMap<>();
        basicCard.put("title", "마스크정보조회");
        basicCard.put("description", "어제입고시간 및 재고정보를 확인하세요!");
        HashMap<String, String> thumbnail = new HashMap<>();
        thumbnail.put("imageUrl", "https://hswsns.s3.ap-northeast-2.amazonaws.com/img/portfolio/mask4.jpg");
        basicCard.put("thumbnail", thumbnail);

        HashMap<String, Object> profile = new HashMap<>();
        profile.put("imageUrl", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4BJ9LU4Ikr_EvZLmijfcjzQKMRCJ2bO3A8SVKNuQ78zu2KOqM");
        profile.put("nickname", "somini");

        HashMap<String, Object> social = new HashMap<>();
        social.put("like", "1238");
        social.put("comment", "8");
        social.put("share", "780");

        ArrayList<HashMap<String,String>> buttonsParent = new ArrayList<>();
        HashMap<String,String> buttonsChild1 = new HashMap<>();
        buttonsChild1.put("action","message");
        buttonsChild1.put("label", "어제입고시간");
        buttonsChild1.put("messageText","약국입고시간");
        buttonsParent.add(buttonsChild1);
        HashMap<String,String> buttonsChild2 = new HashMap<>();
        buttonsChild2.put("action","message");
        buttonsChild2.put("label", "재고약국조회");
        buttonsChild2.put("messageText","약국마스크");
        buttonsParent.add(buttonsChild2);

        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        HashMap<String,Object> output = new HashMap<>();
        basicCard.put("profile", profile);
        basicCard.put("social", social);
        basicCard.put("buttons", buttonsParent);
        output.put("basicCard",basicCard);
        outputs.add(output);

        this.template.put("outputs", outputs);
    }

}
