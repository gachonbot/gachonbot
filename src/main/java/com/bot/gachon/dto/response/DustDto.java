package com.bot.gachon.dto.response;

import com.bot.gachon.domain.GachonDust;
import com.bot.gachon.domain.GachonMask;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class DustDto {
    private List<GachonDust> list;

    public DustDto() {
    }
    @Builder
    public DustDto(List<GachonDust> list) {
        this.list = list;
    }

    public ArrayList<GachonDust> CurrentEntity() {
        ArrayList<GachonDust> entityList = new ArrayList<>();
        for (GachonDust sub : list) {
            entityList.add(GachonDust.builder()
                    .dataTime(sub.getDataTime())
                    .so2Value(sub.getSo2Value())
                    .coValue(sub.getCoValue())
                    .o3Value(sub.getO3Value())
                    .pm10Value(sub.getPm10Value())
                    .pm25Value(sub.getPm10Value())
                    .pm10Grade1h(sub.getPm10Grade1h())
                    .pm25Grade1h(sub.getPm25Grade1h())
                    .build());
        }
        return entityList;
    }
}