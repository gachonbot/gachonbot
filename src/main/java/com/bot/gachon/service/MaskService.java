//package com.bot.gachon.service;
//
//import com.bot.gachon.domain.GachonMask;
//import com.bot.gachon.domain.GachonMaskRepository;
//import com.bot.gachon.domain.GachonYesterdayMask;
//import com.bot.gachon.domain.GachonYesterdayRepository;
//import com.bot.gachon.dto.req.BotRequest;
//import com.bot.gachon.dto.res.*;
//import com.bot.gachon.util.constant.Url;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//import java.util.*;
//import java.util.concurrent.CompletableFuture;
//import java.util.stream.Collectors;
//@Slf4j
//@Service
//public class MaskService {
//
//
//    private final GachonMaskRepository gachonMaskRepository;
//    private final RestTemplate restTemplate;
//    private final GachonYesterdayRepository gachonYesterdayRepository;
//
//    public MaskService(GachonMaskRepository gachonMaskRepository, RestTemplate restTemplate, GachonYesterdayRepository gachonYesterdayRepository) {
//        this.gachonMaskRepository = gachonMaskRepository;
//        this.restTemplate = restTemplate;
//        this.gachonYesterdayRepository = gachonYesterdayRepository;
//    }
//
//
//
//    @CacheEvict(value = "remainMask")
//    @Scheduled(fixedDelay = 300000)
//    public MaskDto updateMaskInfo() {
//
//        URI url = URI.create(Url.MASK_URL);
//        MaskDto response = restTemplate.getForObject(url, MaskDto.class);
//
//        ArrayList<GachonMask> list = response.toCurrentEntitiy();
//        for (GachonMask gachonMask : list) {
//            gachonMaskRepository.save(gachonMask);
//        }
//
//        return response;
//    }
//    public MaskMenuDto findMaskInfo(BotRequest botRequest) {
//
//        return MaskMenuDto.builder().build();
//    }
//
//    public MaskReplayResponse getMaskInfo(BotRequest botRequest){
//        List<String> maskKeyword = Arrays.asList("plenty", "some", "few");
//        List<CompletableFuture<List<GachonMask>>> completableFutures = maskKeyword.stream()
//                .map(keyword -> CompletableFuture.supplyAsync(()
//                        -> gachonMaskRepository
//                        .findAllByRemainStat(keyword)
//                        .orElse(Collections.emptyList())))
//                .collect(Collectors.toList());
//
//        List<GachonMask> items = completableFutures.stream().map(CompletableFuture::join)
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//        ArrayList<MaskReplyResponse_sub> item = new ArrayList<>();
//        for(int i = 0; i<items.size(); i++){
//            MaskReplyResponse_sub sub = MaskReplyResponse_sub.builder().title(items.get(i).getName()
//            ).description(items.get(i).getAddr()).build();
//            item.add(sub);
//        }
//
//        return MaskReplayResponse.builder().items(item).build();
//    }
//
//
//
//    public MaskYesterdayResponse getYesterdayInfo(BotRequest botRequest) {
//        List<GachonYesterdayMask> yesterdayList = gachonYesterdayRepository.findAll();
//
//        StringBuilder yesterdayContent = new StringBuilder();
//        for (int i = 0; i < yesterdayList.size(); i++) {
//            yesterdayContent.append("· 약국이름。 ").append(yesterdayList.get(i).getName())
//                    .append("\n· 약국주소。 ").append(yesterdayList.get(i).getAddr())
//                    .append("\n· 어제입고시간。 " )
//                    .append(yesterdayList.get(i).getStockAt() == null ? "" : yesterdayList.get(i).getStockAt());
//
//            if(i != yesterdayList.size()){
//                yesterdayContent.append("\n\n");
//            }
//        }
//        return MaskYesterdayResponse.builder().content(yesterdayContent.toString()).build();
//    }
//    @Cacheable(value = "remainMask")
//    public List<GachonMask> getRemainMaskInfo() {
//        List<String> maskKeyword = Arrays.asList("plenty", "some", "few");
//        List<CompletableFuture<List<GachonMask>>> completableFutures = maskKeyword.stream()
//                .map(keyword -> CompletableFuture.supplyAsync(()
//                        -> gachonMaskRepository
//                        .findAllByRemainStat(keyword)
//                        .orElse(Collections.emptyList())))
//                .collect(Collectors.toList());
//
//
//
//        return completableFutures.stream()
//                .map(CompletableFuture::join)
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//    }
//
//    @Scheduled(cron = "0 55 23 * * *")
//    public MaskDto saveYesterdayMaskInfo() {
//
//        URI url = URI.create(Url.MASK_URL);
//        MaskDto response_yesterday = restTemplate.getForObject(url, MaskDto.class);
//
//        ArrayList<GachonYesterdayMask> list = response_yesterday.toYesterdayEntitiy();
//        for (GachonYesterdayMask gachonYesterdayMask : list) {
//            gachonYesterdayRepository.save(gachonYesterdayMask);
//        }
//
//        return response_yesterday;
//    }
//    public List<GachonYesterdayMask> findYesterdayMaskInfo() {
//
//        return gachonYesterdayRepository.findAll();
//    }
//}
