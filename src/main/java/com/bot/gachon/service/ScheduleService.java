package com.bot.gachon.service;

import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.ScheduleReponse;
import com.bot.gachon.dto.res.ScheudleMonthMenu;
import com.bot.gachon.dto.res.ScheudleMonthMenu_D;
import com.bot.gachon.util.constant.Url;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ScheduleService {

    public ScheduleReponse getSchedulInfo(BotRequest botRequest) throws IOException {

        String temp = botRequest.getUserRequest().getUtterance();
        temp= temp.substring(0,(temp.length()-1));
        String dayofMonth = temp+"월";
        Document doc = Jsoup.connect(Url.SCHEDULE).get();
        Element e = doc.getElementById("toggle-view");
        String image ="https://s3.ap-northeast-2.amazonaws.com/gachonbot/vision.png";
        String content = "";

        for (Element child : e.children()) {
            if ((child.getElementsByTag("a").text()).equals(dayofMonth)) {
                Elements dlList = child.getElementsByTag("div").get(0).getElementsByTag("dl");
                for(int i = 0 ; i < dlList.size() ; i++){
                    content+="#"+ dlList.get(i).getElementsByTag("dd").text() + "\n" + dlList.get(i).getElementsByTag("dt").text();
                    if(i != dlList.size()-1){
                        content += "\n\n";
                    }
                }
            }
            content = content.replaceAll("\\[학사지원팀\\]","");

        }
        return ScheduleReponse.builder().image(image).content(content).build();
    }

    public ScheudleMonthMenu getSchedulMenu(BotRequest botRequest) {

        return ScheudleMonthMenu.builder().build();
    }
    public ScheudleMonthMenu_D getSchedulMenu2(BotRequest botRequest) {

        return ScheudleMonthMenu_D.builder().build();
    }
}