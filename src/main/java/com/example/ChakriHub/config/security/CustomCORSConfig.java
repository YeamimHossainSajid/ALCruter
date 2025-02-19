package com.example.ChakriHub.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

public class CustomCORSConfig implements CorsConfigurationSource {

    @Override
    public CorsConfiguration getCorsConfiguration(@NotNull HttpServletRequest request) {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // Allow all origins with a workaround for credentials (specific wildcard *)
        corsConfig.addAllowedOriginPattern("*");  // Replaces setAllowedOrigins("*")

        // Allow all methods (GET, POST, PUT, DELETE, OPTIONS)
        corsConfig.setAllowedMethods(Collections.singletonList("*"));

        // Allow credentials (cookies, etc.)
        corsConfig.setAllowCredentials(true);

        // Allow all headers
        corsConfig.setAllowedHeaders(Collections.singletonList("*"));

        // Expose Authorization header if required by frontend
        corsConfig.setExposedHeaders(Collections.singletonList("Authorization"));

        // Max age of CORS preflight cache
        corsConfig.setMaxAge(3600L);

        return corsConfig;
    }
}

