package com.bot.gachon.service;

public enum  GuideUrl {
    notice(Url.GUIDE_URL_NOTICE), news(Url.GUIDE_URL_NEWS), event(Url.GUIDE_URL_EVENT)
        , benefit(Url.GUIDE_URL_BENEFIT);
    public String link = "";

    GuideUrl(String link) {
        this.link = link;
    }

}
