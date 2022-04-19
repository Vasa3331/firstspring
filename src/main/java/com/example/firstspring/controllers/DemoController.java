package com.example.firstspring.controllers;

import com.example.firstspring.service.CurrencyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@Controller
public class DemoController {

    private com.example.firstspring.service.CurrencyService currencyService;

    @Autowired
    public DemoController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping ("/currencyRate")
    public String testController(Model model) {
        model.addAttribute("currancy", currencyService.getUsdRate());
        return "Курс доллара";
    }
}
