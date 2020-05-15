package com.bot.gachon.service;

import com.bot.gachon.domain.GachonMask;
import com.bot.gachon.domain.GachonMaskRepository;
import com.bot.gachon.domain.GachonYesterdayMask;
import com.bot.gachon.domain.GachonYesterdayRepository;
import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Slf4j
@Service
public class GachonService {

    private final GachonMaskRepository gachonMaskRepository;
    private final RestTemplate restTemplate;
    private final GachonYesterdayRepository gachonYesterdayRepository;

    public GachonService(GachonMaskRepository gachonMaskRepository, RestTemplate restTemplate, GachonYesterdayRepository gachonYesterdayRepository) {
        this.gachonMaskRepository = gachonMaskRepository;
        this.restTemplate = restTemplate;
        this.gachonYesterdayRepository = gachonYesterdayRepository;
    }

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
            image = "https://s3.ap-northeast-2.amazonaws.com/gachonbot/areum.png";
        }
         else if (url.equals("vision")) {
            image = "https://s3.ap-northeast-2.amazonaws.com/gachonbot/vision.png";
        }
         else image = "https://s3.ap-northeast-2.amazonaws.com/gachonbot/areum.png";

        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        String yo = "";
        switch (dayOfWeek){
            case 1:
                yo ="일요일";
                break;
            case 2:
                yo ="월요일";
                break;
            case 3:
                yo ="화요일";
                break;
            case 4:
                yo ="수요일";
                break;
            case 5:
                yo ="목요일";
                break;
            case 6:
                yo = "금요일";
                break;
            case 7:
                yo = "토요일";
                break;
        }
        yo = "월요일";
        String menu ="";
        String content;
        for (Element child : e.children()) {
           if (yo.equals(child.getElementsByTag("img").attr("alt"))) {
               for (Element child2 : child.getElementsByTag("dl").get(0).children()){
                   menu += child2.getElementsByTag("dd").toString();
               }
//               menu = menu.replaceAll("<br>","\n");
//               menu = menu.replaceAll("<dd>","");
//               menu = menu.replaceAll("</dd>","");
//               System.out.println("test1" + menu);
            }
           menu = menu.replaceAll("\n\n", "\n");
        }
        return HaksikResponse.builder().menu(menu).image(image).build();
    }


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

    public LibraryResponse getInfo(BotRequest botRequest) throws IOException{
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



    @CacheEvict(value = "remainMask")
    @Scheduled(fixedDelay = 300000)
    public MaskDto updateMaskInfo() {

        URI url = URI.create(Url.MASK_URL);
        MaskDto response = restTemplate.getForObject(url, MaskDto.class);

        ArrayList<GachonMask> list = response.toCurrentEntitiy();
        for (GachonMask gachonMask : list) {
            gachonMaskRepository.save(gachonMask);
        }

        return response;
    }


    public MaskMenuDto findMaskInfo(BotRequest botRequest) {

        return MaskMenuDto.builder().build();
    }

    public MainMenuDto getMainmenu(BotRequest botRequest) {

        return MainMenuDto.builder().build();
    }



    public MaskReplayResponse getMaskInfo(BotRequest botRequest){
        List<String> maskKeyword = Arrays.asList("plenty", "some", "few");
        List<CompletableFuture<List<GachonMask>>> completableFutures = maskKeyword.stream()
                .map(keyword -> CompletableFuture.supplyAsync(()
                        -> gachonMaskRepository
                        .findAllByRemainStat(keyword)
                        .orElse(Collections.emptyList())))
                .collect(Collectors.toList());

        List<GachonMask> items = completableFutures.stream().map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        ArrayList<MaskReplyResponse_sub> item = new ArrayList<>();
        for(int i = 0; i<items.size(); i++){
            MaskReplyResponse_sub sub = MaskReplyResponse_sub.builder().title(items.get(i).getName()
            ).description(items.get(i).getAddr()).build();
            item.add(sub);
        }

        return MaskReplayResponse.builder().items(item).build();
    }



    public MaskYesterdayResponse getYesterdayInfo(BotRequest botRequest) {
        List<GachonYesterdayMask> yesterdayList = gachonYesterdayRepository.findAll();

        StringBuilder yesterdayContent = new StringBuilder();
        for (int i = 0; i < yesterdayList.size(); i++) {
            yesterdayContent.append("· 약국이름。 ").append(yesterdayList.get(i).getName())
                    .append("\n· 약국주소。 ").append(yesterdayList.get(i).getAddr())
                    .append("\n· 어제입고시간。 " )
                    .append(yesterdayList.get(i).getStockAt() == null ? "" : yesterdayList.get(i).getStockAt());

            if(i != yesterdayList.size()){
                yesterdayContent.append("\n\n");
            }
        }
        return MaskYesterdayResponse.builder().content(yesterdayContent.toString()).build();
    }

    public WeatherResponse getWeatherInfo2(BotRequest botRequest) {

        URI url = URI.create(Url.WEATHER_URL);
        WeatherDto responseEntity = null;
        responseEntity = restTemplate.getForObject(url, WeatherDto.class);


        return WeatherResponse.builder().status(responseEntity.getWeather().get(0).getDescription())
                .detail("현재기온: " + responseEntity.getMain().getTemp() + "ºC\n"
                        + "오늘의 최고기온: " + responseEntity.getMain().getTemp_max() + "ºC\n"
                        + "오늘의 최저기온: " + responseEntity.getMain().getTemp_min() + "ºC\n"
                        + "습도: " + responseEntity.getMain().getHumidity()+"%").build();


    }

    @Cacheable(value = "remainMask")
    public List<GachonMask> getRemainMaskInfo() {
        List<String> maskKeyword = Arrays.asList("plenty", "some", "few");
        List<CompletableFuture<List<GachonMask>>> completableFutures = maskKeyword.stream()
                .map(keyword -> CompletableFuture.supplyAsync(()
                        -> gachonMaskRepository
                        .findAllByRemainStat(keyword)
                        .orElse(Collections.emptyList())))
                .collect(Collectors.toList());



        return completableFutures.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "0 55 23 * * *")
    public MaskDto saveYesterdayMaskInfo() {

        URI url = URI.create(Url.MASK_URL);
        MaskDto response_yesterday = restTemplate.getForObject(url, MaskDto.class);

        ArrayList<GachonYesterdayMask> list = response_yesterday.toYesterdayEntitiy();
        for (GachonYesterdayMask gachonYesterdayMask : list) {
            gachonYesterdayRepository.save(gachonYesterdayMask);
        }

        return response_yesterday;
    }
    public List<GachonYesterdayMask> findYesterdayMaskInfo() {

        return gachonYesterdayRepository.findAll();
    }
}


