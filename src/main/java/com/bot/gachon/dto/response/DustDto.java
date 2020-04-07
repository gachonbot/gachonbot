package com.bot.gachon.dto.response;

import com.bot.gachon.domain.GachonDust;
import com.bot.gachon.domain.GachonMask;
import lombok.Builder;
import lombok.Getter;
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
        for (GachonDust dust : list) {
            entityList.add(GachonDust.builder()
                    .dataTime(dust.getdataTime())
                    .pm10Grade1h(dust.getpm10Grade1h())
                    .pm25Grade1h(dust.getpm25Grade1h())
                    .pm10Value(dust.getpm10Value())
                    .pm25Value(dust.getpm10Value())
                    .build());
        }
        return entityList;
    }
}