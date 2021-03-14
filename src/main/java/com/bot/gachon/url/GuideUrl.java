package com.bot.gachon.url;

import com.bot.gachon.util.constant.Url;

public enum  GuideUrl {
    notice(Url.GUIDE_URL_NOTICE), news(Url.GUIDE_URL_NEWS), benefit(Url.GUIDE_URL_BENEFIT);
    public String link = "";

    GuideUrl(String link) {
        this.link = link;
    }

}
