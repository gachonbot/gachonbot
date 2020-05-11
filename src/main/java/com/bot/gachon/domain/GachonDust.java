package com.bot.gachon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class GachonDust {
    @Id
    @Column(name="yyyymmdd")
    private String dataTime;
    private String so2Value;
    private String coValue;
    private String o3Value;
    private String pm10Value;
    private String pm25Value;
    private String pm10Grade1h;
    private String pm25Grade1h;

    @Builder
    public GachonDust(
                                   String dataTime,
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

        String result_pm10;
        String result_pm25;
        switch(pm10Grade1h){
            case "1" :
                result_pm10 = "좋음";
                break;
            case "2" :
                result_pm10 = "보통";
                break;
            case "3" :
                result_pm10 = "나쁨";
                break;
            case "4" :
                result_pm10 = "아주나쁨";
                break;
            default:
                result_pm10 = "데이터 없음";
        }
        switch(pm25Grade1h){
            case "1" :
                result_pm25 = "좋음";
                break;
            case "2" :
                result_pm25 = "보통";
                break;
            case "3" :
                result_pm25 = "나쁨";
                break;
            case "4" :
                result_pm25 = "아주나쁨";
                break;
            default:
                result_pm25 = "데이터 없음";
        }

        this.pm10Grade1h = result_pm10;
        this.pm25Grade1h = result_pm25;
    }

}