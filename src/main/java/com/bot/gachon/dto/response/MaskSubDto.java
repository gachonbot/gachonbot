package com.bot.gachon.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MaskSubDto {
    private String addr;
    private String created_at;
    private String name;
    private String remain_stat;
    private String stock_at;
    private String type;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemain_stat() {
        return remain_stat;
    }

    public void setRemain_stat(String remain_stat) {
        this.remain_stat = remain_stat;
    }

    public String getStock_at() {
        return stock_at;
    }

    public void setStock_at(String stock_at) {
        this.stock_at = stock_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
