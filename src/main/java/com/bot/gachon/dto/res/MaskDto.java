package com.bot.gachon.dto.res;

import com.bot.gachon.domain.GachonMask;
import com.bot.gachon.domain.GachonYesterdayMask;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class MaskDto {
    private int count;
    private List<MaskSubDto> stores;

    public MaskDto() {}

    @Builder
    public MaskDto(int count, List<MaskSubDto> stores) {
        this.count = count;
        this.stores = stores;
    }

    public ArrayList<GachonMask> toCurrentEntitiy() {
        ArrayList<GachonMask> entitiyList = new ArrayList<>();
        for (MaskSubDto sub : stores) {
            entitiyList.add(GachonMask.builder()
                                      .addr(sub.getAddr())
                                      .code(sub.getCode())
                                      .createdAt(sub.getCreated_at())
                                      .lat(sub.getLat())
                                      .lng(sub.getLng())
                                      .name(sub.getName())
                                      .remainStat(sub.getRemain_stat())
                                      .stockAt(sub.getStock_at())
                                      .type(sub.getType())
                                      .build());
        }
        return entitiyList;
    }
    public ArrayList<GachonYesterdayMask> toYesterdayEntitiy(){
        ArrayList<GachonYesterdayMask> entitiyList = new ArrayList<>();
        for (MaskSubDto sub : stores) {
            entitiyList.add(GachonYesterdayMask.builder()
                    .addr(sub.getAddr())
                    .code(sub.getCode())
                    .createdAt(sub.getCreated_at())
                    .lat(sub.getLat())
                    .lng(sub.getLng())
                    .name(sub.getName())
                    .remainStat(sub.getRemain_stat())
                    .stockAt(sub.getStock_at())
                    .type(sub.getType())
                    .build());
        }
        return entitiyList;
    }

}



