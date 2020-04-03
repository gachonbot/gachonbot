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

    public WeatherDto findWeatherInfo() throws Exception {

        URI url = URI.create(Url.WEATHER_URL);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.getForEntity(url, String.class);

        String jsonInfo = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        WeatherDto weatherDto = mapper.readValue(jsonInfo, WeatherDto.class);
        return weatherDto;
    }

    public HaksikDto findHaksikInfo(String building) throws IOException {

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
    //parksomin test
    public MaskMenuDto findMaskInfo(BotRequest botRequest) {
//        List<GachonMask> maskList = gachonMaskRepository.findAll();

//        ArrayList<MaskReplyResponse_sub> items = new ArrayList<>();
//        for(int i = 0; i< 10 ; i++){
//            MaskReplyResponse_sub sub = MaskReplyResponse_sub.builder().title(maskList.get(0).getName()
//            ).description(maskList.get(0).getAddr() + "\n" + maskList.get(0).getRemainStat()).build();
//            items.add(sub);
//        }

        MaskReplayResponse.builder().build();

        return MaskMenuDto.builder().build();
    }
    @Cacheable(value = "remainMask")
    public MaskReplayResponse replayResponse(BotRequest botRequest){
        List<GachonMask> maskList = gachonMaskRepository.findAll();

        ArrayList<MaskReplyResponse_sub> items = new ArrayList<>();
        for(int i = 0; i< 10 ; i++){
            MaskReplyResponse_sub sub = MaskReplyResponse_sub.builder().title(maskList.get(i).getName()
            ).description(maskList.get(i).getAddr() + "\n" + maskList.get(i).getRemainStat()).build();
            items.add(sub);

        }


        return MaskReplayResponse.builder().items(items).build();
    }


    public MaskYesterdayResponse findYesterdayInfo(BotRequest botRequest) {
        List<GachonYesterdayMask> yesterdayList = gachonYesterdayRepository.findAll();
        String content = "# 약국이름 : "+ yesterdayList.get(0).getName()
                + "\n# 약국주소 : " + yesterdayList.get(0).getAddr()
                +"\n# 어제입고시간 : "+ yesterdayList.get(0).getStockAt();

        return MaskYesterdayResponse.builder().content(content).build();
    }

    public List<GachonMask> findMaskInfo() {

        return gachonMaskRepository.findAll();
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
    public MaskDto getYesterdayMaskInfo() {

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


