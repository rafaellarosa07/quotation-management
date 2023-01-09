package com.inatel.quotation.management.service;

import com.inatel.quotation.management.model.entity.Quote;
import com.inatel.quotation.management.repository.QuoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class QuoteService {

    private QuoteRepository quoteRepository;
    private ModelMapper mapper;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, ModelMapper mapper) {
        this.quoteRepository = quoteRepository;
        this.mapper = mapper;
    }

    public Quote save(Quote quote){
        return quoteRepository.save(quote);
    }

    public Map<LocalDate, BigDecimal> getQuoteMap(String stockId) {
        List<Quote> byStockIdId = findByStockId(stockId);
        Map<LocalDate, BigDecimal> quotesMap = new HashMap<>();
        byStockIdId.stream().forEach(quote -> {
            quotesMap.put(quote.getDate(), quote.getPrice());
        });
        return quotesMap;
    }


    public List<Quote> findByStockId(String stockId){
        return quoteRepository.findByStockId(stockId);
    }


}
