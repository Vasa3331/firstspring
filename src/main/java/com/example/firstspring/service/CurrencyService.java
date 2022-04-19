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

    @Scheduled(fixedRate = 5000)
    public String getUsdRate() {
        Document doc = null;

        try {
            doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements d = doc.select("tbody>tr>td:contains(Доллар США)~td");
        p = d.text();
        return p;
    }



}
