package com.inatel.quotation.management.controller;

import com.inatel.quotation.management.configuration.ApplicationConfig;
import com.inatel.quotation.management.service.CacheService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
class StockCacheControllerTest {


    private static final String PATH = "/stockCache";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CacheService cacheService;


    @Test
    void deleteCache() throws Exception {
        doNothing().when(cacheService).clearCache();
        this.mockMvc.perform(delete(PATH)).andExpect(status().isOk());
    }
}