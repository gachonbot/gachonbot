package com.bot.gachon.dto.response;

import java.util.List;

public class HaksikDto {
    private List<HaksikSubDto> allMenu;

    public List<HaksikSubDto> getAllMenu() {
        return allMenu;
    }

    public void setAllMenu(List<HaksikSubDto> allMenu) {
        this.allMenu = allMenu;
    }
}
