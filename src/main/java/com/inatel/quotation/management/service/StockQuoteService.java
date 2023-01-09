package com.inatel.quotation.management.service;

import com.inatel.quotation.management.model.dto.StockQuoteDTO;
import com.inatel.quotation.management.model.entity.Quote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockQuoteService {

    private QuoteService quoteService;
    private ModelMapper mapper;
    private StockManagerService stockManagerService;

    @Autowired
    public StockQuoteService(QuoteService quoteService, ModelMapper mapper,
                             StockManagerService stockManagerService) {
        this.quoteService = quoteService;
        this.mapper = mapper;
        this.stockManagerService = stockManagerService;
    }


    @Transactional
    public StockQuoteDTO create(StockQuoteDTO stock) throws Exception {
        stockManagerService.validateStock(stock);
        saveQuotes(stock);
        return stock;
    }

    private void saveQuotes(StockQuoteDTO stock){
        stock.getQuotes().forEach((quoteDate, quotePrice) -> {
            quoteService.save(Quote.builder().price(quotePrice)
                    .date(quoteDate)
                    .stockId(stock.getStockId()).build());
        });
    }

    public List<StockQuoteDTO> getAll() {
        var stocks = stockManagerService.getStockManager();
        var stocksQuote = stocks.stream().map(stock -> {
            var stockQuote = mapper.map(stock, StockQuoteDTO.class);
            fillStockQuote(stockQuote, stockQuote.getId());
            return stockQuote;
        }).collect(Collectors.toList());
        return stocksQuote;
    }

    public StockQuoteDTO findById(String stockId) throws Exception {
        var stockQuote = stockManagerService.getStockManager()
                .stream().filter(stock -> stock.getId().equals(stockId))
                .findAny().orElseThrow(Exception::new);

        var stockQuoteDTO = mapper.map(stockQuote, StockQuoteDTO.class);
        fillStockQuote(stockQuoteDTO, stockId);
        return stockQuoteDTO;
    }

    private StockQuoteDTO fillStockQuote(StockQuoteDTO stockQuote, String stockId) {
        stockQuote.setQuotes(quoteService.getQuoteMap(stockId));
        return stockQuote;
    }

}
