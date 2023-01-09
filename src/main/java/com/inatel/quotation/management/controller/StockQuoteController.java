package com.inatel.quotation.management.controller;

import com.inatel.quotation.management.model.dto.StockQuoteDTO;
import com.inatel.quotation.management.service.StockQuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/quote")
public class StockQuoteController {

    private StockQuoteService stockQuoteService;

    @Autowired
    public StockQuoteController(StockQuoteService stockQuoteService) {
        this.stockQuoteService = stockQuoteService;
    }


    @PostMapping()
    @Operation(
            summary = "Create a Stock Quote",
            description = "Create a Stock Quote",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> create(@RequestBody StockQuoteDTO stock) throws Exception {
        stockQuoteService.create(stock);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Return all Stock Quotes",
            description = "Read all Stock Quotes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(stockQuoteService.getAll(), HttpStatus.OK);
    }


    @GetMapping("stockId")
    @Operation(
            summary = "Get Stock Quote By ID",
            description = "Read a Stock Quote by stockId",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> getById(@RequestParam("stockId") String stockId) throws Exception {
        return new ResponseEntity<>(stockQuoteService.findById(stockId), HttpStatus.OK);
    }

}


