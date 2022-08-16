# TradeIt - Stock Info App
---
## Features
API to find the stock information like name, average price, minimum price and maximum price for last week.
Daily historical data is stored in H2 database whenever fetched from API. If the data is not present in database for a stock on a particular day, API call is made.
A separate API is created to download the JSON data for a particular stock code

## Assumptions
API will share stock data for a particular company one at a time. 
For multiple stocks, list of codes can be fetched from user in the HTTP body and processed accordingly.

## How to run the project
Run the tradeit.shivani.shah.stocks.controller.StockController class as Java application to run the Spring boot project. Default Tomcat server provided by Spring boot is used to serve the API.
Or
Run the jar embedded in target folder using below command
java -jar tradeit-0.0.1.jar

Once the server is up and running, use below API (GET) to fetch data.
 
http://localhost:3000/api/stocks/fetch/GOOG
http://localhost:3000/api/stocks/download/GOOG

## Alternate Solutions
UI can be made for getting input of Stock code from user and API can be called to show the data on UI. Download button can be added and download API can be called to get the json file.
Stock code can be auto completed based on user input and SYMBOL_SEARCH API can be used to find matching Stock codes.

### Author
Shivani Shah (email: shivani3693@gmail.com)

### Credits
All data is fetched from API hosted at https://www.alphavantage.co/