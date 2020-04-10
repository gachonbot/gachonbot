package com.bot.gachon.service;

import com.bot.gachon.dto.res.MaskDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@Slf4j
public class GachonServiceTest {

    @Test
    public void test() {
        String url = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByGeo/json?lat=37.450694&lng=127.128638&m=1500";
        RestTemplate restTemplate = new RestTemplate();
        MaskDto response = restTemplate.getForObject(url, MaskDto.class);
    }

}