
# Forex Data API - Subtask 1

This project is part of a larger application that scrapes historical exchange rate data from Yahoo Finance and provides a REST API for querying the stored data. In Subtask 1 of Task 2, we have built a REST API around the scraped data to query historical exchange rates for specified currency pairs over given periods.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Setup and Usage](#setup-and-usage)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Error Handling](#error-handling)
- [Example Output](#example-output)
- [Limitations](#limitations)
- [Contact](#contact)

## Introduction

The Forex Data API allows users to query historical exchange rate data for specific currency pairs within a defined period. The data is retrieved from Yahoo Finance and includes fields such as Date, Open, High, Low, Close, Adjusted Close, and Volume. This subtask focuses on building and testing the query capabilities of the REST API.

## Features

- Query historical exchange rate data for specified currency pairs.
- Support for predefined periods such as 1W (1 week), 1M (1 month), 3M (3 months), 6M (6 months), and 1Y (1 year).
- Provides JSON responses for easy integration with other applications.

## Tech Stack

- **Java 11**
- **Spring Boot 2.5.4**
- **H2 Database** - In-memory database for storing and querying scraped data.
- **Postman** - For API testing

## Setup and Usage
1. Build the project:
    ```bash
    mvn clean install
    ```

2. Run the application:
    ```bash
    mvn spring-boot:run
    ```

3. Access the H2 console:
    - URL: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: (leave blank)

## API Endpoints

### Scrape Data
- **POST /api/v1/exchange/scrape**: Scrapes exchange rate data from Yahoo Finance and stores it in the database.
    - Parameters:
        - `quote`: Currency pair to scrape (e.g., `EURUSD=X`).
        - `fromDate`: Unix timestamp for the start date.
        - `toDate`: Unix timestamp for the end date.

### Query Data
- **POST /api/v1/exchange/forex-data**: Queries historical exchange rate data between given periods.
    - Parameters:
        - `from`: From currency code (e.g., GBP, AED).
        - `to`: To currency code (e.g., INR).
        - `period`: Time period (e.g., 1W, 1M, 3M, 6M, 1Y).

Example Request:
```
POST /api/v1/exchange/forex-data?from=EUR&to=USD&period=1M
```

### API Response Example
```json
[
    {
        "id": 1,
        "quote": "EURUSD=X",
        "date": "2024-07-15",
        "open": 1.0886,
        "high": 1.0922,
        "low": 1.0884,
        "close": 1.0886,
        "adjClose": 1.0886,
        "volume": null
    },
    ...
]
```

## Database Schema

The data is stored in a single table `EXCHANGE_RATE`

### Database Schema Image
![Database Schema](./img/db.png)

## Error Handling

- If the data for a specific field is not available or malformed, it is stored as `null` or `0` (for volume).
- If no data is found for the given query parameters, an empty list is returned.

### Error Message Example
![Error Message](../img/error_message.png)

## Example Output

### Postman Output
![Postman Output](../img/postman_output.png)

### Console Logs
![Console Logs](../img/console_logs.png)

## limitations

- The scraping URL is called without any query parameter checking (i.e may be called twice if we hit the API for same parameter twice).
- This results in the database storing redundant duplicate data.
- The query API also calls the DB and checks for the passed parameters, hence the redundant/duplicate data is called and fetched N number of times.

## Contact

For any queries, feel free to reach out:

- **Name**: Saksham Tiwari
- **Email**: saksham21.work@gmail.com
- **LinkedIn**: [Saksham Tiwari](https://www.linkedin.com/in/saksham-tiwari/)

---
