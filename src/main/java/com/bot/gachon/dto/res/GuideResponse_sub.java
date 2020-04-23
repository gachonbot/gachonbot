package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
@Getter
public class GuideResponse_sub {

    private String title;
    private String description;
    private String imageUrl;
    private HashMap<String,String> link = new HashMap<>();

    public GuideResponse_sub(){}

    @Builder
    public GuideResponse_sub(String title, String description, String imageUrl, HashMap<String,String> link,String web){
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.link.put("web",web);
    }

}
