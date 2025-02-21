//package com.example.ChakriHub.service.impl;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.Map;
//
//
//@Service
//public class JobsApiService {
//
//    private final WebClient webClient;
//
//    private static final String API_URL = "https://api.apijobs.dev/v1/job/search";
//    private static final String API_KEY = "YOUR_API_KEY_HERE"; // Replace with actual API key
//
//    public JobApiService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl(API_URL).build();
//    }
//
//    public String searchJobs(String query) {
//        return webClient.post()
//                .uri("")
//                .header("apikey", API_KEY) // Set API key
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .bodyValue(Map.of("q", query))
//                .retrieve()
//                .bodyToMono(String.class) // Convert response to String
//                .block(); // Synchronous call
//    }
//}
//
