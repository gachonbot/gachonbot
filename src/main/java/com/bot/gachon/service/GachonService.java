package com.bot.gachon.service;

import com.bot.gachon.domain.GachonMask;
import com.bot.gachon.domain.GachonMaskRepository;
import com.bot.gachon.domain.GachonYesterdayMask;
import com.bot.gachon.domain.GachonYesterdayRepository;
import com.bot.gachon.dto.req.BotRequest;
import com.bot.gachon.dto.res.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

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

    public WeatherDto findWeatherInfo() throws Exception {

        URI url = URI.create(Url.WEATHER_URL);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.getForEntity(url, String.class);

        String jsonInfo = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        WeatherDto weatherDto = mapper.readValue(jsonInfo, WeatherDto.class);
        return weatherDto;
    }

    public HaksikDto getHaksikInfo(String building) throws IOException {

        HaksikUrl haksikUrl = HaksikUrl.valueOf(building);
        Document doc = Jsoup.connect(haksikUrl.link).get();
        Element e = doc.getElementById("toggle-view");

        HaksikDto haksikDto = new HaksikDto();
        List<HaksikSubDto> haksikSubDtoList = new ArrayList<>();

        String today = new SimpleDateFormat("E요일").format(new Date());

        for (Element child : e.children()) {
            if (today.equals(child.getElementsByTag("img").attr("alt"))) {
                HaksikSubDto haksikSubDto = new HaksikSubDto();
                haksikSubDto.setDay(child.getElementsByTag("img").attr("alt"));
                haksikSubDto.setMenu(child.text());
                haksikSubDtoList.add(haksikSubDto);
                break;
            }
        }

        haksikDto.setAllMenu(haksikSubDtoList);
        return haksikDto;
    }


    public GuideResponse getNoticeInfo(BotRequest botRequest) throws IOException {

        System.out.println("# test : " + botRequest.getUserRequest().getUtterance());

        String urlKeyword = "";
        if (botRequest.getUserRequest().getUtterance().equals("장학소식테스트")) {
            urlKeyword = "benefit";
        } else if (botRequest.getUserRequest().getUtterance().equals("공지사항테스트")) {
            urlKeyword = "notice";
        } else if (botRequest.getUserRequest().getUtterance().equals("취업소식테스트")){
            urlKeyword = "news";
        }
        //test
        else{
            urlKeyword = "news";
        }

        GuideUrl guideUrl = GuideUrl.valueOf(urlKeyword);
        Document doc = Jsoup.connect(guideUrl.link).get();
        Elements e = doc.getElementsByClass("list");

        ArrayList<GuideResponse_sub> item = new ArrayList<>();
        for (Element child : e.get(0).children().get(0).children()) {
            if ("공지".equals(child.getElementsByTag("img").attr("alt")))
                continue;
            GuideResponse_sub sub = GuideResponse_sub.builder().web(child.getElementsByTag("a").attr("href"))
                    .description(child.getElementsByClass("data").text()).title(child.getElementsByTag("a").text())
                    .imageUrl("http://k.kakaocdn.net/dn/APR96/btqqH7zLanY/kD5mIPX7TdD2NAxgP29cC0/1x1.jpg").build();
            item.add(sub);

            if(item.size() == 4) break;

        }
        return GuideResponse.builder().items(item).build();
    }

    public LibraryResponse getInfo(BotRequest botRequest) throws IOException{
        Document doc = Jsoup.connect(Url.LIBRARY_CENTRAL).get();
        Elements e = doc.getElementsByTag("tbody");
        System.out.println("testtest");
        ArrayList<LibraryResponse_sub> item = new ArrayList<>();
        for(Element child : e.get(0).children()){
            LibraryResponse_sub sub = LibraryResponse_sub.builder().title(child.getElementsByClass("left").text())
                    .description(child.getElementsByClass("right bold").text()+"/"
                            +child.getElementsByClass("last right bold blue bg_blu").text()).imageUrl("https://s3.ap-northeast-2.amazonaws.com/gachonbot/notice.png").build();
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



    @Cacheable(value = "remainMask")
    public List<GachonMask> getRemainMaskInfo() {
        List<String> maskKeyword = Arrays.asList("plenty", "some", "few");
        List<CompletableFuture<List<GachonMask>>> completableFutures = maskKeyword.stream()
                .map(keyword -> CompletableFuture.supplyAsync(()
                        -> gachonMaskRepository
                        .findAllByRemainStat(keyword)
                        .orElse(Collections.emptyList())))
                .collect(Collectors.toList());

        System.out.println(completableFutures.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
        System.out.println(completableFutures.get(0));

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
        System.out.println(response_yesterday);

        return response_yesterday;
    }
    public List<GachonYesterdayMask> findYesterdayMaskInfo() {

        return gachonYesterdayRepository.findAll();
    }
}


