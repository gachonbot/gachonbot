package com.bot.gachon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@NoArgsConstructor
@Table(name = "GACHON_DUST")
@Getter
@Entity
public class GachonDust {
    @Id
    @Column(name="yyyymmdd")
    private String dataTime;
    private String pm10Grade1h;
    private String pm25Grade1h;
    private String pm10Value;
    private String pm25Value;

    @Builder
    public GachonDust(String dataTime,
                      String pm10Grade1h,
                      String pm25Grade1h,
                      String pm10Value,
                      String pm25Value) {
        this.dataTime = dataTime;
        this.pm10Grade1h = pm10Grade1h;
        this.pm25Grade1h = pm25Grade1h;
        this.pm10Value = pm10Value;
        this.pm25Value = pm25Value;
    }
}

/*
public class Dust {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="yyyymmdd")
    private String dataTime;

    @Column
    private String pm10Grade1h;

    @Column
    private String pm25Grade1h;

    
    public String getDataTime(){
        return dataTime;
    }
    public void setDataTime(String dataTime){
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
*/