package com.example.forex_data_scheduler.repository;

import com.example.forex_data_scheduler.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    List<ExchangeRate> findByQuoteAndDateBetween(String quote, LocalDate startDate, LocalDate endDate);

    Optional<ExchangeRate> findByQuoteAndDate(String quote, LocalDate date); // New method to find a specific record
}
