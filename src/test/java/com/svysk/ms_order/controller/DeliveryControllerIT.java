package com.svysk.ms_order.controller;


import com.svysk.openapi.client.DeliveriesApi;
import com.svysk.openapi.dto.DeliveryDto;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class DeliveryControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveriesApi deliveriesApi;

    @Test
    void shouldReturnDeliveriesWhenServiceIsAvailable() throws Exception {

        // Mocking the behavior of deliveriesApi to return a successful list of deliveries
        DeliveryDto delivery1 = new DeliveryDto();
        delivery1.setId(1L);
        DeliveryDto delivery2 = new DeliveryDto();
        delivery2.setId(2L);
        List<DeliveryDto> deliveries = Arrays.asList(
                delivery1,
                delivery2
        );

        Mockito.when(deliveriesApi.getDeliveries()).thenReturn(deliveries);

        // Sending the request
        mockMvc.perform(get("/deliveries"))
                .andExpect(status().isOk())  // Check status 200 OK
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    void shouldReturnServiceUnavailableWhenCircuitBreakerTriggers() throws Exception {
        // Mocking an error to activate the fallback
        Mockito.when(deliveriesApi.getDeliveries()).thenThrow(new RuntimeException("Service Unavailable"));

        // Sending the request
        mockMvc.perform(get("/deliveries"))
                .andExpect(status().isServiceUnavailable())  // Return status 503
                .andExpect(jsonPath("$.error").value("Delivery service is currently unavailable. Please try again later."));
    }
}
