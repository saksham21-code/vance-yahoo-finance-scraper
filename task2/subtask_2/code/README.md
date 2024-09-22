# Forex Data Scheduler - Subtask 2
This project is part of a larger application that scrapes historical exchange rate data from Yahoo
Finance and provides a REST API for querying the stored data. In Subtask 2 of Task 2, we have
implemented a scheduled job to periodically scrape data for specific currency pairs and store it in
the in-memory H2 database while addressing duplicate data issues.
## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Setup and Usage](#setup-and-usage)
- [Scheduled Jobs](#scheduled-jobs)
- [API Endpoints](#api-endpoints)
- [Duplicate Data Handling](#duplicate-data-handling)
- [Database Schema](#database-schema)
- [Error Handling](#error-handling)
- [Example Output](#example-output)
- [Limitations](#limitations)
- [Contact](#contact)
## Introduction
The Forex Data Scheduler periodically scrapes historical exchange rate data for specific currency
pairs from Yahoo Finance. This subtask automates the data scraping process for predefined
currency pairs and periods using Spring's scheduling capabilities, while also implementing a
strategy to avoid storing duplicate data.
## Features
- Periodic data scraping for specified currency pairs.
- Duplicate data handling to ensure only new data is stored.
- Configurable schedules for weekly, monthly, quarterly, semi-annual, and annual scraping.
- Stores data in an in-memory H2 database for easy access and testing.
## Tech Stack
- **Spring Boot 2.5.4**
- **Spring Scheduling** - For automated data scraping.
- **H2 Database** - In-memory database for storing and querying scraped data.
- **Jsoup** - For web scraping.
## Project Structure
```plaintext
forex-data-scheduler/
|
|- src/
| |- main/
| | |- java/
| | | `- com/example/forex_data_scheduler/
| | | |- controller/ # REST API Controllers
| | | | `- ExchangeController.java
| | | |
| | | |- model/ # Entity classes
| | | | `- ExchangeRate.java
| | | |
| | | |- repository/ # Repository interfaces for database operations
| | | | `- ExchangeRateRepository.java
| | | |
| | | |- service/ # Service classes containing business logic
| | | | |- ExchangeRateQueryService.java
| | | | |- ExchangeScraperService.java
| | | | `- SchedulerService.java
| | | |
| | | `- utils/ # Utility classes
| | | `- DateUtils.java
| | | `- DateRangeUtils.java
| | |
| | `- resources/
| | |- application.properties # Configuration properties for the application
| | `- data.sql # Initial data for H2 database (if any)
| |
| `- test/ # Test classes for unit and integration testing
|
|- .gitignore # Files and directories to be ignored by Git
|- pom.xml # Maven project file containing dependencies and build configuration
`- README.md # Project documentation
```
## Setup and Usage
### Build the project:
```bash
mvn clean install
```
### Run the application:
```bash
mvn spring-boot:run
```
### Access the H2 console:
- **URL:** http://localhost:8080/h2-console
- **JDBC URL:** jdbc:h2:mem:testdb
- **Username:** sa
- **Password:** (leave blank)
## Scheduled Jobs
The application has the following scheduled scraping jobs configured:
- **INR-USD (for testing purposes)**
 - Every 10 seconds: Scrapes data for the `INRUSD=X` currency pair for the last 1 month.
- **GBP-INR**
 - Weekly (Every Monday at midnight): Scrapes data for the `GBPINR=X` currency pair for the last
week.
 - Monthly (First day of every month at midnight): Scrapes data for the last month.
 - Quarterly (Every 3 months at midnight on the first day): Scrapes data for the last 3 months.
## API Endpoints
1. **GET /api/exchange-rates/{currency}**
 - Retrieves the historical exchange rates for the specified currency.
2. **POST /api/scrape**
 - Initiates the scraping process for the configured currency pairs.
## Duplicate Data Handling
The application uses a strategy to ensure only new data is stored. Before inserting new data into the
database, the application checks if data for the given date and currency pair already exists.
## Database Schema
The database schema consists of a single table `exchange_rate` with the following fields:
- `id` (INT) - Primary key.
- `currency` (VARCHAR) - The currency pair (e.g., 'EURUSD=X').
- `date` (DATE) - The date of the exchange rate.
- `open` (DOUBLE) - Opening rate.
- `high` (DOUBLE) - Highest rate of the day.
- `low` (DOUBLE) - Lowest rate of the day.
- `close` (DOUBLE) - Closing rate.
- `adj_close` (DOUBLE) - Adjusted closing rate.
- `volume` (LONG) - Trading volume.
## Error Handling
The application handles various errors such as:
- Network errors during scraping.
- Invalid currency pair errors.
- Database connection issues.
## Example Output
```json
{
 "currency": "INRUSD=X",
 "date": "2024-09-21",
 "open": 73.45,
 "high": 73.89,
 "low": 73.21,
 "close": 73.55,
 "adj_close": 73.55,
 "volume": 1500000
}
```
## Limitations
- The application currently supports only a few currency pairs for testing.
- The data is stored in an in-memory database, which is not suitable for production use.
## Contact
For any questions or feedback, please contact:
- **Email:** your.email@example.com
- **GitHub:** [your-github-username](https://github.com/your-github-username)
