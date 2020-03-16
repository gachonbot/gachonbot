package com.bot.gachon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "GACHON_MASK", indexes = {@Index(columnList = "remain_stat")})
public class GachonMask {

    @Id
    private String name;
    private String addr;
    private String code;
    private double lat;
    private double lng;
    private String remain_stat;
    private String created_at;
    private String stock_at;
    private String type;

    @Builder
    public GachonMask(String addr,
                      String code,
                      String created_at,
                      double lat,
                      double lng,
                      String name,
                      String remain_stat,
                      String stock_at,
                      String type) {
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


