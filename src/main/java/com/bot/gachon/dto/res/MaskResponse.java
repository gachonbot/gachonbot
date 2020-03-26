package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MaskResponse {
    private List<Content> contents;

    @Getter
    @Builder
    public static class Content {
        private String type;
        private String text;
    }
}