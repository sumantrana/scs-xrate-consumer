package com.sumant.learning.messaging.xrateconsumer.scsxrateconsumer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateRecord, String> {
}
