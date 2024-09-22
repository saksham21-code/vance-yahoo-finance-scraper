
# Forex Data API - Subtask 1

This subtask is part of a larger project aimed at building a REST API around the historical exchange rate data scraped from Yahoo Finance. The goal of this subtask is to implement and test a REST API that allows querying of stored exchange rate data for different currency pairs and periods.

## Table of Contents
- [Project Structure](#project-structure)
- [Classes Overview](#classes-overview)
- [API Endpoints](#api-endpoints)
- [Usage](#usage)
- [Error Handling](#error-handling)
- [Improvements](#improvements)
- [Contact](#contact)

## Project Structure
```
forex-data-api/
│
├── src/main/java/com/example/forex_data_api/
│   ├── controller/
│   │   └── ExchangeController.java
│   ├── model/
│   │   └── ExchangeRate.java
│   ├── repository/
│   │   └── ExchangeRateRepository.java
│   ├── service/
│   │   ├── ExchangeRateQueryService.java
│   │   └── ExchangeScraperService.java
│   ├── utils/
│   │   └── DateUtils.java
│   └── ForexDataApiApplication.java
│
└── src/main/resources/
    ├── application.properties
    └── data.sql
```

## Classes Overview
- **ExchangeController**: Handles incoming HTTP requests for scraping data and querying historical exchange rates.
- **ExchangeRate**: Entity representing the exchange rate data stored in the database.
- **ExchangeRateRepository**: Repository interface for CRUD operations on `ExchangeRate` entities.
- **ExchangeRateQueryService**: Contains the business logic for querying historical exchange rates based on user inputs.
- **ExchangeScraperService**: Handles the web scraping logic for fetching historical exchange rates from Yahoo Finance.
- **DateUtils**: Utility class for date parsing and conversion.

## API Endpoints

### 1. Scrape Exchange Data
- **URL**: `/api/v1/exchange/scrape`
- **Method**: `POST`
- **Parameters**:
    - `quote` (String): Currency pair to scrape (e.g., `EURUSD=X`).
    - `fromDate` (long): Unix timestamp for start date.
    - `toDate` (long): Unix timestamp for end date.
- **Description**: Scrapes exchange rate data for the specified currency pair and date range and stores it in the database.

### 2. Query Historical Exchange Rates
- **URL**: `/api/v1/exchange/forex-data`
- **Method**: `POST`
- **Parameters**:
    - `from` (String): From currency code (e.g., `GBP`).
    - `to` (String): To currency code (e.g., `INR`).
    - `period` (String): Time period (e.g., `1W`, `1M`, `3M`, `6M`, `1Y`).
- **Description**: Fetches historical exchange rate data for the given currency pair and time period.

## Usage

1. **Build the Project**:
    ```bash
    mvn clean install
    ```

2. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

3. **Access the API**:
    - Use Postman or any other API client to send requests to the defined endpoints.
    - Ensure the H2 console is accessible for viewing stored data.

4. **Check Database**:
    - Access the H2 console at `http://localhost:8080/h2-console`.
    - Use the following details to connect:
      - **JDBC URL**: `jdbc:h2:mem:testdb`
      - **Username**: `sa`
      - **Password**: (leave blank)

## Error Handling
- If the `scrape` API is called with an invalid `quote`, the system will log an error.
- If the `forex-data` API is called with no matching data, an empty list is returned.

## Improvements
1. **Duplicate Data Handling**:
    - Add logic to prevent duplicate entries in the database if the same data is scraped multiple times.

2. **Enhanced Query Parameters**:
    - Allow for more flexible date range querying (e.g., custom date ranges instead of fixed periods).

3. **Error Handling**:
    - Implement more detailed error messages and status codes for various failure scenarios.

## Contact
For any queries, feel free to reach out:

- **Name**: Saksham Tiwari
- **Email**: saksham21.work@gmail.com
- **LinkedIn**: [Saksham Tiwari](https://www.linkedin.com/in/saksham-tiwari/)

