package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class MaskReplyResponse_sub {

//    private HashMap<String,Object> item_sub = new HashMap<>();
    private String title;
    private String description;
    private HashMap<String,Object> item_sub = new HashMap<>();
    public MaskReplyResponse_sub(){}

    @Builder
    public MaskReplyResponse_sub(String label, String blockId){
        item_sub.put("title","마스크재고");
        item_sub.put("description","재고가 얼마나 있을까?");
        HashMap<String,String> thumbnailChild = new HashMap<>();
        thumbnailChild.put("imageUrl"," 우왕");
        item_sub.put("thumbnail",thumbnailChild);
        ArrayList<HashMap<String,String>> buttonsParent = new ArrayList<>();
        HashMap<String,String> buttonsChild = new HashMap<>();
        buttonsChild.put("action","block");
        buttonsChild.put("label", label);
        buttonsChild.put("blockId",blockId);
        buttonsParent.add(buttonsChild);
        item_sub.put("buttons",buttonsParent);


    }
}


