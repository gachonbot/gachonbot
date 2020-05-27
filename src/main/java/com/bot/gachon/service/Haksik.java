package com.bot.gachon.service;

import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.HaksikResponse;
import com.bot.gachon.url.HaksikUrl;
import com.bot.gachon.url.Url;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Calendar;


@Slf4j
@Service
public class Haksik {
    public HaksikResponse getHaksikInfo_domitory(BotRequest botRequest) throws IOException {

        Document doc = Jsoup.connect(Url.HAKSIK_URL_DOMITORY).get();
        Elements e = doc.getElementsByTag("tbody");
        String image ="https://s3.ap-northeast-2.amazonaws.com/gachonbot/giusk.png";
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        String yo = "";
        switch (dayOfWeek) {
            case 1:
                yo = "일";
                break;
            case 2:
                yo = "월";
                break;
            case 3:
                yo = "화";
                break;
            case 4:
                yo = "수";
                break;
            case 5:
                yo = "목";
                break;
            case 6:
                yo = "금";
                break;
            case 7:
                yo = "토";
                break;
        }
        String menu = "";
        for (Element child : e.get(0).children()) {
            if ((child.getElementsByTag("th").text()).indexOf(yo) != -1) {
                menu += child.text();
            }
        }
        return HaksikResponse.builder().menu(menu).image(image).build();

    }


    public HaksikResponse getHaksikInfo(String url) throws IOException {

        HaksikUrl haksikUrl = HaksikUrl.valueOf(url);
        Document doc = Jsoup.connect(haksikUrl.link).get();
        Element e = doc.getElementById("toggle-view");
        String image = "";

        if (url.equals("art")) {
            image = "https://s3.ap-northeast-2.amazonaws.com/gachonbot/art.png";
        } else if (url.equals("vision")) {
            image = "https://s3.ap-northeast-2.amazonaws.com/gachonbot/vision.png";
        } else {
            image = "https://s3.ap-northeast-2.amazonaws.com/gachonbot/areum.png";
        }

        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        String yo = "";
        switch (dayOfWeek) {
            case 1:
            case 7:
                yo = "토요일"; // 일요일은 데이터가 없기 때문에 토요일 데이터 제공.
                break;
            case 2:
                yo = "월요일";
                break;
            case 3:
                yo = "화요일";
                break;
            case 4:
                yo = "수요일";
                break;
            case 5:
                yo = "목요일";
                break;
            case 6:
                yo = "금요일";
                break;
        }
        String menu = "";
        String content;
        for (Element child : e.children()) {
            if (yo.equals(child.getElementsByTag("img").attr("alt"))) {
                for (Element child2 : child.getElementsByTag("dl").get(0).getElementsByTag("dd")) {
                    menu += child2.toString();
                    if (yo.equals("토요일") && ! menu.equals("")){
                        break;
                    }
                }

            }
        }
        return HaksikResponse.builder().menu(menu).image(image).build();
    }
}


