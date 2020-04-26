package com.bot.gachon.service;

public enum  HaksikUrl {
    vision(Url.HAKSIK_URL_ART), art(Url.HAKSIK_URL_VISION), edu(Url.HAKSIK_URL_EDU);
    public String link = "";

    HaksikUrl(String link) {
        this.link = link;
    }
}

