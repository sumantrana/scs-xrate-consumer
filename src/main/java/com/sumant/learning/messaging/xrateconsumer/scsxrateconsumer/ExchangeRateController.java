package com.sumant.learning.messaging.xrateconsumer.scsxrateconsumer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExchangeRateController {

    private ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateController(final ExchangeRateRepository exchangeRateRepository){
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @GetMapping("/")
    public String getDataAndUI(Model model){
        List<ExchangeRateRecord> exchangeRateRecordList = exchangeRateRepository.findAll();
        model.addAttribute("rates", exchangeRateRecordList);
        return "exchangeRate";
    }
}
