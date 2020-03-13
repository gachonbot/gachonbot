package com.bot.gachon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MaskSubDto {

    private String addr;
    private String code;
    private String created_at;
    private double lat;
    private double lng;
    private String name;
    private String remain_stat;
    private String stock_at;
    private String type;

    public MaskSubDto(){}

    @Builder
    public MaskSubDto(String addr,
                     String code,
                     String created_at,
                     double lat,
                     double lng,
                     String name,
                     String remain_stat,
                     String stock_at,
                     String type){
       this.addr = addr;
       this.code = code;
       this.created_at = created_at;
       this.lat = lat;
       this.lng = lng;
       this.name = name;
       this.type = type;
   }
}
