package com.example.forex_data_api.repository;

import com.example.forex_data_api.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    List<ExchangeRate> findByQuoteAndDateBetween(String quote, LocalDate startDate, LocalDate endDate);
}
