package com.bot.gachon.service;

import com.bot.gachon.dto.response.SampleResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GachonServiceTest {

    @Autowired
    private GachonService gachonService;

    @Test
    public void sampleTest() {
        SampleResponse sampleResponse = gachonService.doSample("gachon");
        System.out.println(sampleResponse.getUserId());
    }

}