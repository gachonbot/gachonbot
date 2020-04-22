package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LibraryResponse_sub {
    private String title;
    private String description;
    private String imageUrl;

    public LibraryResponse_sub(){}

    @Builder
    public LibraryResponse_sub(String title, String description,String imageUrl){
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

}
