package com.sumant.learning.messaging.xrateconsumer.scsxrateconsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value={ "id" }, allowGetters=true)
public class ExchangeRateRecord {

    @Id
    String id;
    String fromCurrency;
    String toCurrency;
    Double rate;
}
