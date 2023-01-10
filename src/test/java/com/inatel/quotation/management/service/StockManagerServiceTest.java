package com.inatel.quotation.management.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.inatel.quotation.management.client.SockManagerWebClient;
import com.inatel.quotation.management.model.dto.StockDTOClient;
import com.inatel.quotation.management.model.dto.StockQuoteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StockManagerServiceTest {

    @InjectMocks
    private StockManagerService stockManagerService;
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SockManagerWebClient sockManagerWebClient;

    private StockDTOClient stockDTOClient;

    @BeforeEach
    void setUp() {
        stockDTOClient = new StockDTOClient();
        stockDTOClient.setId("teste");
        stockDTOClient.setDescription("teste_description");

    }

    @Test
    void getStockManager() {
        Gson gson = new Gson();
        when(sockManagerWebClient.getAllStockManager())
                .thenReturn(gson.toJson(Arrays.asList(stockDTOClient)));

        var stockManagerClient = stockManagerService.getStockManager();

        Assertions.assertEquals(stockManagerClient, Arrays.asList(stockDTOClient));
    }

    @Test
    void validateStock() throws Exception {
        Gson gson = new Gson();
        when(sockManagerWebClient.getAllStockManager())
                .thenReturn(gson.toJson(Arrays.asList(stockDTOClient)));

        stockManagerService.validateStock(new StockQuoteDTO("teste", "teste", null));

        verify(sockManagerWebClient).getAllStockManager();
    }

    @Test
    void validateStockErrorStockDoesntExist() throws Exception {
        Gson gson = new Gson();
        when(sockManagerWebClient.getAllStockManager())
                .thenReturn(gson.toJson(Arrays.asList(stockDTOClient)));


        Assertions.assertThrows(Exception.class, () ->
                stockManagerService.validateStock(new StockQuoteDTO("teste2", "teste2", null)));
    }
}