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


        ArrayList<HashMap<String,String>> buttonsParent = new ArrayList<>();
        HashMap<String,String> buttonsChild1 = new HashMap<>();
        buttonsChild1.put("action","message");
        buttonsChild1.put("label", "어제 약국별 마스크입고시간");
        buttonsChild1.put("messageText","마스크입고시간");
        buttonsParent.add(buttonsChild1);
        HashMap<String,String> buttonsChild2 = new HashMap<>();
        buttonsChild2.put("action","message");
        buttonsChild2.put("label", "학교기준 재고많은 약국조회");
        buttonsChild2.put("messageText","재고기준 약국조회");
        buttonsParent.add(buttonsChild2);

        HashMap<String,String> buttonsChild3 = new HashMap<>();
        buttonsChild2.put("action","message");
        buttonsChild2.put("label", "학교기준 가까운 약국조회");
        buttonsChild2.put("messageText","거리기준 약국조회");
        buttonsParent.add(buttonsChild3);

        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        HashMap<String,Object> output = new HashMap<>();
        basicCard.put("buttons", buttonsParent);
        output.put("basicCard",basicCard);
        outputs.add(output);

        this.template.put("outputs", outputs);
    }

}
