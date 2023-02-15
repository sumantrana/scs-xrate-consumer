package com.sumant.learning.messaging.xrateconsumer.scsxrateconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.function.Consumer;

@Configuration
@Slf4j
public class ExchangeRateConsumer {

    private ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateConsumer(final ExchangeRateRepository exchangeRateRepository){
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Bean
    public Consumer<Message<List<ExchangeRateRecord>>> rateConsumer() {
        return (message) -> {
            log.info("Received Message: " + message);
            List<ExchangeRateRecord> payload = message.getPayload();
            payload.stream()
                    .forEach(exchangeRateRecord -> {
                        String rateRecordId = exchangeRateRecord.getFromCurrency() + "-" + exchangeRateRecord.getToCurrency();
                        exchangeRateRecord.setId(rateRecordId);
                        exchangeRateRepository.save(exchangeRateRecord);
                    });
        };
    }

}
