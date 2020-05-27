package com.bot.gachon.service;

import com.bot.gachon.dto.res.GuideResponse;
import com.bot.gachon.dto.res.GuideResponse_sub;
import com.bot.gachon.url.GuideUrl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;


@Slf4j
@Service
public class Guide {


    public GuideResponse getNoticeInfo(String url) throws IOException {

        GuideUrl guideUrl = GuideUrl.valueOf(url);
        Document doc = Jsoup.connect(guideUrl.link).get();
        Elements e = doc.getElementsByClass("list");
        Elements s = doc.getElementsByClass("summary");

        String menu =s.get(0).children().get(0).getElementsByTag("strong").text();

        ArrayList<GuideResponse_sub> item = new ArrayList<>();
        for (Element child : e.get(0).children().get(0).children()) {
            if ("공지".equals(child.getElementsByTag("img").attr("alt")))
                continue;
            GuideResponse_sub sub = GuideResponse_sub.builder().web(child.getElementsByTag("a").attr("href"))
                    .description(child.getElementsByClass("data").text()).title(child.getElementsByTag("a").text())
                    .imageUrl("https://s3.ap-northeast-2.amazonaws.com/gachonbot/noticeicon.png").build();
            item.add(sub);
            if(item.size() == 5) break;

            String menu2 = String.valueOf(GuideResponse.builder().menu(s.get(0).children().get(0).getElementsByTag("strong").text()).build());

        }
        return GuideResponse.builder().items(item).menu(menu).build();
    }


}
