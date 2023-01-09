package com.inatel.quotation.management.service;

import com.inatel.quotation.management.model.dto.StockQuoteDTO;
import com.inatel.quotation.management.model.entity.Quote;
import com.inatel.quotation.management.repository.QuoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

    @InjectMocks
    private QuoteService quoteService;

    @Mock
    private ModelMapper mapper;
    @Mock
    private QuoteRepository quoteRepository;

    private Quote quote;

    @BeforeEach
    void setUp() {
        initialize();
    }

    @Test
    void save() {
        when(quoteRepository.save(quote)).thenReturn(quote);
        var response = quoteService.save(quote);

        assertEquals(response, quote);
    }

    @Test
    void saveThrownNull() {
        quote = null;
        doThrow(NullPointerException.class).when(quoteRepository).save(null);
        Assertions.assertThrows(Exception.class, () ->  quoteService.save(quote));

    }

    @Test
    void getQuoteMap() {
        when(quoteRepository.findByStockId("teste")).thenReturn(Arrays.asList(quote));
        Map<LocalDate, BigDecimal> map = quoteService.getQuoteMap("teste");
        Mockito.verify(quoteRepository).findByStockId("teste");
        assertNotNull(map);
    }

    @Test
    void findByStockId() {
        when(quoteRepository.findByStockId("teste")).thenReturn(Arrays.asList(quote));
        List<Quote> list = quoteService.findByStockId("teste");
        Mockito.verify(quoteRepository).findByStockId("teste");
        assertNotNull(list);
    }

    private void initialize(){
        quote = Quote.builder().price(new BigDecimal(12))
                .date(LocalDate.now())
                .stockId("teste").build();
    }


}