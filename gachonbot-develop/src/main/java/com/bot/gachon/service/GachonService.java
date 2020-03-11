package com.bot.gachon.service;

import com.bot.gachon.dto.response.SampleResponse;
import org.springframework.stereotype.Service;

@Service
public class GachonService {

    public SampleResponse doSample(String userId) {
        SampleResponse sampleResponse = new SampleResponse();
        sampleResponse.setUserId(userId);
        return sampleResponse;
    }
}
