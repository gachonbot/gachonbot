package com.bot.gachon.service;

public enum  GuideUrl {
    notice(Url.GUIDE_URL_NOTICE), news(Url.GUIDE_URL_NEWS), benefit(Url.GUIDE_URL_BENEFIT);
    public String link = "";

    GuideUrl(String url) {
        this.link = link;
    }

}
