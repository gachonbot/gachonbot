package com.bot.gachon.domain;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Getter
@NoArgsConstructor
@Entity
public class Gachon {

    @Id
    private String addr;
    private String code;
    private String created_at;
    private double lat;
    private double lng;
    private String name;
    private String remain_stat;
    private String stock_at;
    private String type;

    @Builder
    public Gachon(String addr, String code, String created_at, double lat, double lng, String name, String remain_stat, String stock_at, String type){
        this.addr = addr;
        this.code = code;
        this.created_at = created_at;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.remain_stat = remain_stat;
        this.stock_at = stock_at;
        this.type = type;
    }
}


