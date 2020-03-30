package com.bot.gachon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Builder
@Entity
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Dust {

    @Id
    @Column(name="nalJJa") // dataTime이 sql 예약어와 겹쳐서 변경
    public String dataTime;

    @Column(name="pm10")
    public String pm10Grade1h;
    @Column(name="pm25")
    public String pm25Grade1h;


    public String getdataTime(){
        return dataTime;
    }
    public void setdataTime(String dataTime){
        this.dataTime = dataTime;
    }


    public String getPm10Grade1h(){
        return pm10Grade1h;
    }
    public void setPm10Grade1h(String pm10Grade1h){
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


    public String getPm25Grade1h(){
        return pm25Grade1h;
    }
    public void setPm25Grade1h(String pm25Grade1h){
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
