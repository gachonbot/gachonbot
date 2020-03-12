package com.bot.gachon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class MaskDto {
    private int count;
    private List<MaskSubDto> stores;

    public MaskDto() {
    }

    @Builder
    public MaskDto(int count, List<MaskSubDto> stores) {
        this.count = count;
        this.stores = stores;
    }
}
