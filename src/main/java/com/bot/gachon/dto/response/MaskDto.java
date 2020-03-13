package com.bot.gachon.dto.response;

import com.bot.gachon.domain.Gachon;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Builder

@Getter
@ToString
public class MaskDto {
    private int count;
    private List<MaskSubDto> stores;

    public MaskDto(){}

    @Builder
    public MaskDto(int count, List<MaskSubDto> stores){
        this.count = count;
        this.stores = stores;
    }

    public ArrayList<Gachon> toEntitiy(){
        ArrayList<Gachon> entitiyList = new ArrayList<>();
        for(MaskSubDto sub : stores){
            entitiyList.add(Gachon.builder()
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

//    public Gachon toEntitiy(MaskSubDto sub){
//        return Gachon.builder()
//                .addr(sub.getAddr())
//                .code(sub.getCode())
//                .created_at(sub.getCreated_at())
//                .lat(sub.getLat())
//                .lng(sub.getLng())
//                .name(sub.getName())
//                .remain_stat(sub.getRemain_stat())
//                .stock_at(sub.getStock_at())
//                .type(sub.getType())
//                .build();
//
//    }
}



