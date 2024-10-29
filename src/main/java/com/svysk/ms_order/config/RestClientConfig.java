package com.svysk.ms_order.config;

import com.svysk.openapi.ApiClient;
import com.svysk.openapi.client.DeliveriesApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

    /**
     * Base path for Delivery microservice
     */
    @Value("${delivery.url}")
    private String deliveryUrl;

    @Bean
    public DeliveriesApi deliveriesApi() {
        var apiClient = new ApiClient(new RestTemplate());
        return new DeliveriesApi(apiClient.setBasePath(deliveryUrl));
    }
}
