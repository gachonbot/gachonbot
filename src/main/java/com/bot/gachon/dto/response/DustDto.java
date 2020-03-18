package com.bot.gachon.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)

public class DustDto {
    private String dataTime;
    private String cityName; //지역
    private String pm10Value;
    private String pm25Value;
    private ArrayList<DustDto> dust;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getPm10Value() {
        return pm10Value;
    }

    public void setOm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }

    public String getPm25Value() {
        return pm25Value;
    }

    public void setPm25Value(String pm25Value) {
        this.pm25Value = pm25Value;
    }

    public ArrayList<DustDto> getDust() {
        return dust;
    }

    public void setDust(ArrayList<DustDto> dust) {
        this.dust = dust;
    }
}
