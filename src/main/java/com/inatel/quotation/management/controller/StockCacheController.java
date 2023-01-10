package com.inatel.quotation.management.controller;

import com.inatel.quotation.management.service.CacheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stockCache")
public class StockCacheController {

    private CacheService cacheService;

    @Autowired
    public StockCacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    @DeleteMapping
    @Operation(
            summary = "Get Stock Quote By ID",
            description = "Read a Stock Quote by stockId",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> deleteCache() throws Exception {
        cacheService.clearCache();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


