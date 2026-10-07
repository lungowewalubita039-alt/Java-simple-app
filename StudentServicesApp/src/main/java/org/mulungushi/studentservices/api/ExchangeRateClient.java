package org.mulungushi.studentservices.api;

// Simulates consuming a REST API via HTTP
public class ExchangeRateClient {
    public String fetchLatestRates() {
        // In a real app, this uses java.net.http.HttpClient
        return "{ \"currency\": \"ZMW\", \"rate\": 26.5 }";
    }
}