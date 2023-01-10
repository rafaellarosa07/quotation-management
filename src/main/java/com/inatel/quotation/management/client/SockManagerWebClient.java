package com.inatel.quotation.management.client;

import com.inatel.quotation.management.model.dto.RegisterDTO;
import com.inatel.quotation.management.model.dto.StockDTOClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SockManagerWebClient {

    private String  uri = "http://localhost:8080";

    private String host = "0.0.0.0";

    @Value("${server.port}")
    private String port;

    private RestTemplate restTemplate;

  public String getAllStockManager(){
      restTemplate = new RestTemplate();
      var result = restTemplate.getForObject(uri+"/stock", String.class);
      return result;
  }

    public Object RegisterNewStock(StockDTOClient stockDTOClient){
        restTemplate = new RestTemplate();
        var result = restTemplate.postForObject(uri+"/stock",stockDTOClient, Object.class);
        return result;
    }

    @Bean
    public Object registerApp(){
        restTemplate = new RestTemplate();
        var result = restTemplate.postForObject(uri+"/notification",
                RegisterDTO.builder().port(port).host(host).build(), Object.class);
        return result;
    }
}