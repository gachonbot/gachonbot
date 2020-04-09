package com.bot.gachon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Table(name = "GACHON_DUST")
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

/*
public class GachonDust {
    @Id
    @Column(name="yyyymmdd")
    private String dataTime;
    private String pm10Grade1h;
    private String pm25Grade1h;
    private String pm10Value;
    private String pm25Value;

    
    public String getdataTime(){
        return dataTime;
    }
    public void setdataTime(String dataTime){
        this.dataTime = dataTime;
    }

    public String getpm10Value(){
        return pm10Value;
    }
    public void setpm10Value(String pm10Value){
        this.pm10Value = pm10Value;
    }

    public String getpm25Value(){
        return pm25Value;
    }
    public void setpm25Value(String pm25Value){
        this.pm25Value = pm25Value;
    }


    public String getpm10Grade1h(){
        return pm10Grade1h;
    }
    public void setpm10Grade1h(String pm10Grade1h){
        String result;
        switch(pm10Grade1h){
            case "1" :
                result = "좋음";
                break;
            case "2" :
                result = "보통";
                break;
            case "3" :
                result = "나쁨";
                break;
            case "4" :
                result = "아주나쁨";
                break;
            default:
                result = "데이터 없음";
        }
        this.pm10Grade1h = result;
    }


    public String getpm25Grade1h(){
        return pm25Grade1h;
    }
    public void setpm25Grade1h(String pm25Grade1h){
        String result;
        switch(pm25Grade1h){
            case "1" :
                result = "좋음";
                break;
            case "2" :
                result = "보통 ";
                break;
            case "3" :
                result = "나쁨";
                break;
            case "4" :
                result = "아주나쁨 ";
                break;
            default:
                result = "데이터 없음";
        }
        this.pm25Grade1h = result;
    }

}
*/