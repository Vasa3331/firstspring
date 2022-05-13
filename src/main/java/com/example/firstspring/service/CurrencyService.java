package com.example.firstspring.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@EnableScheduling
public class CurrencyService {
    
    String p;
    long endTimePoint = 0L;

    @Scheduled(fixedRate = 5000)
    public String getUsdRate() {
        long startTime = System.currentTimeMillis();
        if (endTimePoint == 0L) {
            System.out.println("Первый цикл парсинга. Посчитать промежоток не возможно.");
        } else {
            System.out.println(startTime - endTimePoint);
        }

        Document doc = null;

        try {
            long startTime = System.currentTimeMillis();
            doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements d = doc.select("tbody>tr>td:contains(Доллар США)~td");
        p = d.text();
        endTimePoint = System.currentTimeMillis();
        return p;
    }



}
