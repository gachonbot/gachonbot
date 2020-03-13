package com.bot.gachon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dust {
    private String _returnType;
    private String cityName;
    private String cityNameEng;
    private String coValue;
    private String dataGubun;
    private String dataTime;
    private String districtCode;
    private String districtNumSeq;
    private String itemCode;
    private String khaiValue;
    private String no2Value;
    private String numOfRows;
    private String o3Value;
    private String pageNo;
    private String pm10Value;
    private String pm25Value;
    private String resultCode;
    private String resultMsg;
    private String searchCondition;
    private String serviceKey;
    private String sidoName;
    private String so2Value;
    private String totalCount;

    public Dust() {
    }

    @Builder
    public Dust(String _returnType,
                String cityName,
                String cityNameEng,
                String coValue,
                String dataGubun,
                String dataTime,
                String districtCode,
                String districtNumSeq,
                String itemCode,
                String khaiValue,
                String no2Value,
                String numOfRows,
                String o3Value,
                String pageNo,
                String pm10Value,
                String pm25Value,
                String resultCode,
                String resultMsg,
                String searchCondition, String serviceKey, String sidoName, String so2Value, String totalCount) {
        this._returnType = _returnType;
        this.cityName = cityName;
        this.cityNameEng = cityNameEng;
        this.coValue = coValue;
        this.dataGubun = dataGubun;
        this.dataTime = dataTime;
        this.districtCode = districtCode;
        this.districtNumSeq = districtNumSeq;
        this.itemCode = itemCode;
        this.khaiValue = khaiValue;
        this.no2Value = no2Value;
        this.numOfRows = numOfRows;
        this.o3Value = o3Value;
        this.pageNo = pageNo;
        this.pm10Value = pm10Value;
        this.pm25Value = pm25Value;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.searchCondition = searchCondition;
        this.serviceKey = serviceKey;
        this.sidoName = sidoName;
        this.so2Value = so2Value;
        this.totalCount = totalCount;
    }
}
