package com.example.firstspring.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@Controller
public class DemoController {
    Document doc;

    {
        try {
            doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/")
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Elements d = doc.select("tbody>tr>td:contains(Доллар США)~td");
    String p = d.text();

    @GetMapping ("/currencyRate")
    public String testController(Model model) {
        model.addAttribute("currancy", p);
        return "Курс доллара";
    }
}
