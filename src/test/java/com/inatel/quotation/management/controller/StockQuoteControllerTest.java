package com.inatel.quotation.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inatel.quotation.management.configuration.ApplicationConfig;
import com.inatel.quotation.management.model.dto.StockQuoteDTO;
import com.inatel.quotation.management.service.StockQuoteService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
class StockQuoteControllerTest {

    private static final String PATH = "/quote";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StockQuoteService stockQuoteService;

    private StockQuoteDTO stock;

    @BeforeEach
    void setUp() {
        initializeStockDTO();
    }

    @Test
    void createSuccessfully()throws Exception {
        when(stockQuoteService.create(stock)).thenReturn(stock);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stock)))
                .andExpect(status().isCreated());
    }

    @Test
    void createSuccessfullyBadRequest()throws Exception {
        when(stockQuoteService.create(stock)).thenReturn(stock);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAll() throws Exception {
        when(stockQuoteService.getAll()).thenReturn(Arrays.asList(stock));
        mockMvc.perform(get(PATH)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Is.is("c01cede4-cd45")));
    }

    @Test
    void getById() throws Exception {
        long id = 1L;
        when(stockQuoteService.findById("aapl34")).thenReturn(stock);
        this.mockMvc.perform(get(PATH + "/stockId")
                        .param("stockId", "aapl34")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is("c01cede4-cd45")));
    }


    private void initializeStockDTO(){
        stock = new StockQuoteDTO();
        stock.setStockId("aapl34");
        stock.setId("c01cede4-cd45");
        stock.setQuotes(new HashMap<>());
        stock.getQuotes().put(LocalDate.now(), new BigDecimal(12));
    }
}