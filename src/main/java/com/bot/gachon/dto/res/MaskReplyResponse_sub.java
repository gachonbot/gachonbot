package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class MaskReplyResponse_sub {

    private ArrayList<HashMap<String,Object>> items = new ArrayList<>();

    public MaskReplyResponse_sub(){}

    @Builder
    public MaskReplyResponse_sub(String content){
        HashMap<String, Object> item = new HashMap<>();
        item.put("title", "마스킂고");

        HashMap<String, String> thumbnailChild = new HashMap<>();
        thumbnailChild.put("imageUrl", "가천대주변 약국의 마스크재고입니다.");
        item.put("thumbnail", thumbnailChild);
        ArrayList<HashMap<String,String>> buttonsParent = new ArrayList<>();
        HashMap<String,String> buttonsChild = new HashMap<>();
        buttonsChild.put("action","message");
        buttonsChild.put("label","조회하기");
        buttonsChild.put("messageText","조회되었습니다.");
        buttonsParent.add(buttonsChild);
        item.put("buttons",buttonsParent);

    }

    }


