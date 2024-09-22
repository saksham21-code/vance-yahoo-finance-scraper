# vance-yahoo-finance-scraper.
A scraper application backend that will scrape data from yahoo finance for the historical data. 

# Incremental Approach to Building the Forex Data Scheduler

## Overview

This project is aimed at building a robust and scalable application for scraping and querying historical exchange rate data from Yahoo Finance. The project was developed incrementally, with each task and subtask building upon the previous one. This README explains the approach taken to tackle each task, highlighting the improvements made and how limitations were overcome.

## Approach

### Task 1: Initial Scraping and Data Storage

In Task 1, the focus was on building a basic scraper to fetch historical exchange rate data for specific currency pairs. The data was stored in an in-memory H2 database for ease of access and testing. This phase helped establish the foundation for data extraction and storage mechanisms.

- **Key Features Implemented:**
  - Basic data scraping using Jsoup.
  - Data storage in H2 in-memory database.
  - Initial setup for handling currency pairs and timeframes.

- **Limitations:**
  - Lack of a REST API to query stored data.
  - No mechanism for automated or periodic scraping.
  - Basic error handling with limited data validation.

### Task 2: Building a REST API Around the Scraped Data

#### Sub-task 1: API Development for Querying Historical Data

The next step was to develop a REST API to query the scraped data. This was achieved by implementing a controller that exposed endpoints for querying exchange rate data between given periods.

- **Key Features Implemented:**
  - `POST /api/forex-data` endpoint to query historical data based on currency pairs and periods.
  - Integration with the in-memory database to fetch and return data.
  - Improved error handling for invalid requests.

- **Limitations:**
  - No periodic scraping to keep the database in sync with Yahoo Finance.
  - The data could become outdated without regular updates.

#### Sub-task 2: Automated Data Synchronization

In Sub-task 2, the focus was on overcoming the limitations identified in the previous tasks. A scheduled job was implemented to periodically scrape data for specific currency pairs, ensuring that the in-memory database remains up-to-date.

- **Key Features Implemented:**
  - Automated scraping using Spring Scheduling, configured for weekly, monthly, quarterly, semi-annual, and annual intervals.
  - Handling of duplicate data to ensure only new information is stored.
  - Synchronized scraping for multiple currency pairs like GBP-INR and AED-INR.

### Final Version: Task 2 - Sub-task 2

The final version of the project, as implemented in Task 2 - Sub-task 2, represents the culmination of the incremental approach. This version overcomes the limitations of the previous tasks by:

1. **Automating the data scraping process** to keep the database synchronized with Yahoo Finance.
2. **Building a robust REST API** to provide real-time access to historical exchange rate data.
3. **Implementing comprehensive error handling** and data validation to ensure data integrity and reliability.

For a detailed look at the final implementation, please refer to Task 2 - Sub-task 2 in the project repository.

## Conclusion

By following an incremental development model, I was able to build and refine the Forex Data Scheduler, adding features and addressing limitations step-by-step. This approach ensured a stable and maintainable codebase, with each iteration building upon the lessons learned from the previous one.

For more details on the final version, please check the [](#) section.
