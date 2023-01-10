package com.inatel.quotation.management.service;

import com.inatel.quotation.management.model.dto.StockDTOClient;
import com.inatel.quotation.management.model.dto.StockQuoteDTO;
import com.inatel.quotation.management.model.entity.Quote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockQuoteServiceTest {

    @InjectMocks
    private StockQuoteService stockQuoteService;
    @Mock
    private QuoteService quoteService;

    @Mock
    private StockManagerService stockManagerService;
    @Mock
    private ModelMapper mapper;

    private StockDTOClient stockDTOClient;

    private StockQuoteDTO stockQuoteDTO;

    @BeforeEach
    void setUp() {
        stockDTOClient = new StockDTOClient();
        stockDTOClient.setDescription("teste");
        stockDTOClient.setId("teste1");

        initializeStockDTO();

    }

    @Test
    void create() throws Exception {
        doNothing().when(stockManagerService).validateStock(stockQuoteDTO);
        when(quoteService.save(Quote.builder().price(new BigDecimal(12))
                .date(LocalDate.now())
                .stockId(stockQuoteDTO.getId()).build())).thenReturn(Quote.builder().build());

        var response = stockQuoteService.create(stockQuoteDTO);

        assertEquals(response, stockQuoteDTO);
    }


    @Test
    void createValidateError() throws Exception {
        doThrow(Exception.class).when(stockManagerService).validateStock(stockQuoteDTO);
        Assertions.assertThrows(Exception.class, () ->  stockQuoteService.create(stockQuoteDTO));
    }

    @Test
    void getAll() {
        when(stockManagerService.getStockManager()).thenReturn(Arrays.asList(stockDTOClient));
        when(mapper.map(any(), eq(StockQuoteDTO.class))).thenReturn(stockQuoteDTO);
        List<StockQuoteDTO> list = stockQuoteService.getAll();
        Mockito.verify(stockManagerService).getStockManager();
        Mockito.verify(mapper).map(stockDTOClient, StockQuoteDTO.class);
        assertNotNull(list);
    }

    @Test
    void findByIdSuccess() throws Exception {
        String id = "teste1";
        when(stockManagerService.getStockManager()).thenReturn(Arrays.asList(stockDTOClient));
        when(mapper.map(stockDTOClient, StockQuoteDTO.class)).thenReturn(stockQuoteDTO);


        stockQuoteService.findById(id);

        verify(stockManagerService).getStockManager();
        verify(mapper).map(stockDTOClient, StockQuoteDTO.class);

    }

    @Test
    void findByIdNotfound() {
        String id = "aapl34";
        when(stockManagerService.getStockManager()).thenReturn(Arrays.asList(stockDTOClient));
        Assertions.assertThrows(Exception.class, () -> stockQuoteService.findById(id));
    }

    private void initializeStockDTO(){
        stockQuoteDTO = new StockQuoteDTO();
        stockQuoteDTO.setStockId("aapl34");
        stockQuoteDTO.setId("aapl34");
        stockQuoteDTO.setQuotes(new HashMap<>());
        stockQuoteDTO.getQuotes().put(LocalDate.now(), new BigDecimal(12));
    }

}