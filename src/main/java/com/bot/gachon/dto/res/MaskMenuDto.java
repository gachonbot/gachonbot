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
        HashMap<String, String> thumbnail = new HashMap<>();
        thumbnail.put("imageUrl", "testUrl");

        HashMap<String, Object> basicCard = new HashMap<>();
        basicCard.put("title", "마스크정보조회");
        basicCard.put("description", "어제입고시간 및 재고정보를 확인하세요!");
        basicCard.put("thumbnail", thumbnail);

        HashMap<String, Object> profile = new HashMap<>();
        basicCard.put("imageUrl", "test2Url");
        basicCard.put("nickname", "somini");

        HashMap<String, Object> social = new HashMap<>();
        basicCard.put("like", "1238");
        basicCard.put("comment", "8");
        basicCard.put("share", "780");

        ArrayList<HashMap<String,String>> buttonsParent = new ArrayList<>();
        HashMap<String,String> buttonsChild1 = new HashMap<>();
        buttonsChild1.put("action","message");
        buttonsChild1.put("label", "어제입고시간");
        buttonsChild1.put("messageText","하잇!");
        buttonsParent.add(buttonsChild1);
        HashMap<String,String> buttonsChild2 = new HashMap<>();
        buttonsChild2.put("action","message");
        buttonsChild2.put("label", "재고약국조회");
        buttonsChild2.put("messageText","하잇!");
        buttonsParent.add(buttonsChild2);

        ArrayList<Object> outputs = new ArrayList<>();
        outputs.add(basicCard);
        outputs.add(profile);
        outputs.add(social);
        outputs.add(buttonsParent);
        this.template.put("outputs", outputs);
    }

}
