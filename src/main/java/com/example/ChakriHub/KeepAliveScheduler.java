package com.example.ChakriHub;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepAliveScheduler {

    private static final Logger logger = LoggerFactory.getLogger(KeepAliveScheduler.class);
    private static final String HEALTHCHECK_URL = "https://chakrihub-1-cilx.onrender.com/healthcheck";

    @Scheduled(fixedRate = 300000) // Every 5 minutes
    public void pingBackend() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject(HEALTHCHECK_URL, String.class);
            logger.info("Pinged backend successfully!");
        } catch (Exception e) {
            logger.error("Failed to ping backend: " + e.getMessage());
        }
    }
}

