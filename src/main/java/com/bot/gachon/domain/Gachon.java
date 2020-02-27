package com.bot.gachon.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor

public class Gachon {

    @Id
    private String name;

    @Column(nullable = false)
    private Double tmep;

    @Column(nullable = false)
    private Long humidity;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTmep() {
        return tmep;
    }

    public void setTmep(Double tmep) {
        this.tmep = tmep;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


