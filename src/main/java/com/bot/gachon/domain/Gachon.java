package com.bot.gachon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Gachon {

    @Id
    private String location;

    @Column(nullable = false)
    private Double tmp;

    @Column(nullable = false)
    private Long hum;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String des;

    @Column(columnDefinition = "TEXT")
    private String des2;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getTmp() {
        return tmp;
    }

    public void setTmp(Double tmp) {
        this.tmp = tmp;
    }

    public Long getHum() {
        return hum;
    }

    public void setHum(Long hum) {
        this.hum = hum;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes2() {
        return des2;
    }

    public void setDes2(String des2) {
        this.des2 = des2;
    }

}
