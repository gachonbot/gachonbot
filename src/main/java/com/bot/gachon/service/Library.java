package com.bot.gachon.service;

import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.LibraryResponse;
import com.bot.gachon.dto.res.LibraryResponse_sub;
import com.bot.gachon.url.Url;
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
public class Library {

    public LibraryResponse getInfo(BotRequest botRequest) throws IOException {
        Document doc = Jsoup.connect(Url.LIBRARY_CENTRAL).get();
        Elements e = doc.getElementsByTag("tbody");

        ArrayList<LibraryResponse_sub> item = new ArrayList<>();
        for(Element child : e.get(0).children()){
            LibraryResponse_sub sub = LibraryResponse_sub.builder().title(child.getElementsByClass("left").text())
                    .description(child.getElementsByClass("right bold").text()+"/"
                            +child.getElementsByClass("last right bold blue bg_blue").text()).imageUrl("https://hswsns.s3.ap-northeast-2.amazonaws.com/img/portfolio/book.jpg").build();
            item.add(sub);
        }
        return LibraryResponse.builder().items(item).build();

    }



}
