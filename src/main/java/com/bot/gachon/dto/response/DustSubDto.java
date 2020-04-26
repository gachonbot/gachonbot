package com.bot.gachon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DustSubDto {

    private String dataTime;
    private String so2Value;
    private String coValue;
    private String o3Value;
    private String pm10Value;
    private String pm25Value;
    private String pm10Grade1h;
    private String pm25Grade1h;


    public DustSubDto(){}

    @Builder
    public DustSubDto( String dataTime,
             String so2Value,
             String coValue,
             String o3Value,
             String pm10Value,
             String pm25Value,
             String pm10Grade1h,
             String pm25Grade1h){
        this.dataTime = dataTime;
        this.so2Value = so2Value;
        this.coValue = coValue;
        this.o3Value = o3Value;
        this.pm10Value = pm10Value;
        this.pm25Value = pm25Value;
        this.pm10Grade1h = pm10Grade1h;
        this.pm25Grade1h = pm25Grade1h;
    }
}
