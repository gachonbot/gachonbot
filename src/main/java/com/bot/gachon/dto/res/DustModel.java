package com.bot.gachon.dto.res;

import com.bot.gachon.dto.Dust;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class DustModel {
    private List<Dust> list;

    public DustModel() {
    }
    @Builder
    public DustModel(List<Dust> list) {
        this.list = list;
    }
}
