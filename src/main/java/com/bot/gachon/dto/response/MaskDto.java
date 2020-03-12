package com.bot.gachon.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaskDto {

    private ArrayList<MaskSubDto> stores;

    public ArrayList<MaskSubDto> getStores() {
        return stores;
    }

    public void setStores(ArrayList<MaskSubDto> stores) {
        this.stores = stores;
    }
}
