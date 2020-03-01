package com.bot.gachon.dto.response;

import java.util.ArrayList;

public class HaksikDto {
    private ArrayList<HaksikSubDto> allMenu;

    public ArrayList<HaksikSubDto> getAllMenu() {
        return allMenu;
    }

    public void setAllMenu(ArrayList<HaksikSubDto> allMenu) {
        this.allMenu = allMenu;
    }
}
