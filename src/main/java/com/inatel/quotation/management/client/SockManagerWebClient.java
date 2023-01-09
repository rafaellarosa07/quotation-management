package com.inatel.quotation.management.client;

import com.inatel.quotation.management.model.dto.StockDTOClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SockManagerWebClient {

    private String  uri = "http://localhost:8080/stock";

    private RestTemplate restTemplate;

  public String getAllStockManager(){
      restTemplate = new RestTemplate();
      var result = restTemplate.getForObject(uri, String.class);
      return result;
  }

    public Object RegisterNewStock(StockDTOClient stockDTOClient){
        restTemplate = new RestTemplate();
        var result = restTemplate.postForObject(uri,stockDTOClient, Object.class);
        return result;
    }
}