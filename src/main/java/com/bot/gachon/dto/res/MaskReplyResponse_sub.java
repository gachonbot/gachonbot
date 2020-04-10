package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

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
