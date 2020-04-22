package com.bot.gachon.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class LibraryResponse {

    private String version;
    private HashMap<String,Object> template = new HashMap<>();

    @Builder
    public LibraryResponse(Object items){
        this.version = "2.0";

        HashMap<String,String> simpleText = new HashMap<>();
        simpleText.put("text", "(잔여좌석/총자석)");

        HashMap<String,String> header = new HashMap<>();
        header.put("title","중앙도서관");
        header.put("imageUrl","http://k.kakaocdn.net/dn/xsBdT/btqqIzbK4Hc/F39JI8XNVDMP9jPvoVdxl1/2x1.jpg");
        HashMap<String,Object> listCard = new HashMap<>();
        listCard.put("header",header);
        listCard.put("items",items);
        HashMap<String,Object> output = new HashMap<>();
        output.put("simpleText",simpleText);
        HashMap<String,Object> output2 = new HashMap<>();
        output2.put("listCard",listCard);

        ArrayList<HashMap<String,Object>> outputs = new ArrayList<>();
        outputs.add(output);
        outputs.add(output2);
        this.template.put("outputs",outputs);
    }

}
