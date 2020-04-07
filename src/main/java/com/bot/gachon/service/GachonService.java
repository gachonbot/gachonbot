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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
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

    public GachonService(GachonMaskRepository gachonMaskRepository,
                         RestTemplate restTemplate,
                         GachonYesterdayRepository gachonYesterdayRepository) {
        this.gachonMaskRepository = gachonMaskRepository;
        this.restTemplate = restTemplate;
        this.gachonYesterdayRepository = gachonYesterdayRepository;
    }

    public WeatherDto getWeatherInfo() throws Exception {

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
        for(int i = 0; i<10; i++){
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
            yesterdayContent.append("# 약국이름 : ").append(yesterdayList.get(i).getName())
                    .append("\n# 약국주소 : ").append(yesterdayList.get(i).getAddr())
                    .append("\n# 어제입고시간 :" )
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




    public DustModel findDust() throws IOException {
//        URI url = URI.create(Url.DUST_URL);
//        ResponseEntity<String> responseEntity = null;
//        responseEntity = restTemplate.getForEntity(url, String.class);
//
//        String jsonInfo = responseEntity.getBody();
//        Map<String, Object> result = new HashMap<>();
//
//        ObjectMapper mapper = new ObjectMapper();
//        DustDto dustDto = mapper.readValue(jsonInfo, DustDto.class);
//        return dustDto;
        DustModel response = restTemplate.getForObject(Url.DUST_URL, DustModel.class);
        return response;
    }
}


