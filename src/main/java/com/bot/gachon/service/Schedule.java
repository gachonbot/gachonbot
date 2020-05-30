package com.bot.gachon.service;

import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.MainMenuDto;
import com.bot.gachon.dto.res.ScheduleReponse;
import com.bot.gachon.dto.res.ScheudleMonthMenu;
import com.bot.gachon.dto.res.ScheudleMonthMenu_D;
import com.bot.gachon.url.Url;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;

@Slf4j
@Service
public class Schedule {

    public ScheduleReponse getSchedulInfo(BotRequest botRequest) throws IOException {

        String temp = botRequest.getUserRequest().getUtterance();
        temp= temp.substring(0,(temp.length()-1));
        Document doc = Jsoup.connect(Url.SCHEDULE).get();
        Element e = doc.getElementById("toggle-view");
        String image ="https://s3.ap-northeast-2.amazonaws.com/gachonbot/vision.png";
        Calendar cal = Calendar.getInstance();
        int dayofMonth = cal.get(Calendar.MONTH)+1;
        String dayofMonth_ = dayofMonth+"월";

        String content = "";

        for (Element child : e.children()) {
            if ((child.getElementsByTag("a").text()).equals(dayofMonth_)) {
                for(Element child2 : child.getElementsByTag("div").get(0).getElementsByTag("dl"))
                content+=child2.text()+"\n";
                content = content.replaceAll("\\[학사지원팀\\]","\n");
            }
        }
        System.out.println("test1"+ botRequest.getUserRequest().getUtterance());
        System.out.println("test2"+ temp);
        System.out.println("test3"+ temp.substring(0,temp.length()-1));
        return ScheduleReponse.builder().image(image).content(content).build();
    }

    public ScheudleMonthMenu getSchedulMenu(BotRequest botRequest) {

        return ScheudleMonthMenu.builder().build();
    }
    public ScheudleMonthMenu_D getSchedulMenu2(BotRequest botRequest) {

        return ScheudleMonthMenu_D.builder().build();
    }
}
