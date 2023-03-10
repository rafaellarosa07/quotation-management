package com.inatel.quotation.management.service;

import com.google.gson.Gson;
import com.inatel.quotation.management.client.SockManagerWebClient;
import com.inatel.quotation.management.model.dto.StockDTOClient;
import com.inatel.quotation.management.model.dto.StockQuoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StockManagerService {

    private SockManagerWebClient sockManagerWebClient;

    private final CacheManager cacheManager;

    @Autowired
    public StockManagerService(SockManagerWebClient sockManagerWebClient, CacheManager cacheManager) {
        this.sockManagerWebClient = sockManagerWebClient;
        this.cacheManager = cacheManager;
    }


    @Cacheable("stocks")
    public List<StockDTOClient> getStockManager(){
        Gson gson = new Gson();
        StockDTOClient[] stockDTOClient = gson.fromJson(sockManagerWebClient.getAllStockManager(), StockDTOClient[].class);
        return Arrays.asList(stockDTOClient);
    }

    public void validateStock(StockQuoteDTO stockQuoteDTO) throws Exception {
        List<StockDTOClient> stockManager = getStockManager();
        Boolean b = stockManager.stream()
                .anyMatch(stock -> stock.getId().equals(stockQuoteDTO.getStockId()));
        if(!b){
            throw new Exception("Stock doesn't exist on Stock API!");
        }
    }
}
