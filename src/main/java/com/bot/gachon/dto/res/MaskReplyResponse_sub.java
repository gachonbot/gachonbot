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
    public MaskReplyResponse_sub(String label, String blockId){
        HashMap<String, Object> item = new HashMap<>();
        item.put("title", "마스크재고");

        HashMap<String, String> thumbnailChild = new HashMap<>();
        thumbnailChild.put("imageUrl", "");
        item.put("thumbnail", thumbnailChild);
        ArrayList<HashMap<String,String>> buttonsParent = new ArrayList<>();
        HashMap<String,String> buttonsChild = new HashMap<>();
        buttonsChild.put("action","block");
        buttonsChild.put("label", label);
        buttonsChild.put("blockId",blockId);

        buttonsParent.add(buttonsChild);
        item.put("buttons",buttonsParent);

    }

    }


