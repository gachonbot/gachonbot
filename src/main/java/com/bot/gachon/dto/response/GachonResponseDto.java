package com.bot.gachon.dto.response;


import com.bot.gachon.domain.Gachon;
import lombok.Getter;

@Getter
public class GachonResponseDto {
    private String location; //지역
    private Double tmp; //온도
    private Long hum; //습도
    private String des; // 기상
    private String des2; //상세설명


    public GachonResponseDto(Gachon entitiy) {
        this.location = entitiy.getLocation();
        this.tmp = entitiy.getTmp();
        this.hum = entitiy.getHum();
        this.des = entitiy.getDes();
        this.des2 = entitiy.getDes2();
    }
}
