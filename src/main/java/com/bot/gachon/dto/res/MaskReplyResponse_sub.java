package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class MaskReplyResponse_sub {

    private String title;
    private String description;

    @Builder
    public MaskReplyResponse_sub(String title, String description){
        this.title = title;
        this.description = description;
    }
}


