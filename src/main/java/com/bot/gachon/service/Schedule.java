package com.bot.gachon.service;

import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.ScheduleReponse;
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
public class Schedule {

    public ScheduleReponse getSchedulInfo(BotRequest botRequest) throws IOException {

        Document doc = Jsoup.connect(Url.SCHEDULE).get();
        Element e = doc.getElementById("toggle-view");
        String image ="https://s3.ap-northeast-2.amazonaws.com/gachonbot/vision.png";
        Calendar cal = Calendar.getInstance();
        int dayofMonth = cal.get(Calendar.MONTH)+1 ;

        String content = "";

        for (Element child : e.children()) {
            if ((child.getElementsByTag("a").text()).indexOf(dayofMonth) != -1) {
                content += child.getElementsByTag("div").text();

                System.out.println("test1"+ child.getElementsByTag("div").text() );
                System.out.println("test2" +child.getElementsByTag("a").text());
                System.out.println("test3"+dayofMonth);
            }
        }
        System.out.println("test1" +e.children().get(0).getElementsByTag("a").text());
        System.out.println("test2"+e.children().get(0).tagName());
        System.out.println("test3"+ e.children().get(0).getElementsByTag("div").text());
        return ScheduleReponse.builder().image(image).content(content).build();
    }
}
