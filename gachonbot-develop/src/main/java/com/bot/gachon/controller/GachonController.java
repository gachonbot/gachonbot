package com.bot.gachon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
public class GachonController {
    @GetMapping("/dust")
    public String callAPI() {
        StringBuffer result = new StringBuffer();
        try {
            String urlstr = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?"
                    + "serviceKey=%2FFMFpVx%2Bvq%2BeEKK6owg7QDf44oVwyYvRL8I8DAo1ZgHpJmeQ0YXKYlelKIafadOJJ5nB%2B1OV1l5pJyKwMuI3Pg%3D%3D"
                    + "&numOfRows=10"
                    + "&pageNo=1"
                    + "&sidoName=%EC%84%9C%EC%9A%B8&searchCondition=DAILY"
                    + "&_returnType=json";

            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

            String returnLine;
            result.append("<xmp>");
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlconnection.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result+"</xmp>";
    }

}
