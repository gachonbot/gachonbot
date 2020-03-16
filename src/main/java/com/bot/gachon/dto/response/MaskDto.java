package com.bot.gachon.dto.response;

import com.bot.gachon.domain.GachonMask;
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

    public ArrayList<GachonMask> toEntitiy() {
        ArrayList<GachonMask> entitiyList = new ArrayList<>();
        for (MaskSubDto sub : stores) {
            entitiyList.add(GachonMask.builder()
                                      .addr(sub.getAddr())
                                      .code(sub.getCode())
                                      .created_at(sub.getCreated_at())
                                      .lat(sub.getLat())
                                      .lng(sub.getLng())
                                      .name(sub.getName())
                                      .remain_stat(sub.getRemain_stat())
                                      .stock_at(sub.getStock_at())
                                      .type(sub.getType())
                                      .build());
        }
        return entitiyList;
    }

}



